package org.pilirion.nakaza.security;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.service.UserService;
import org.pilirion.nakaza.utils.Pwd;

/**
 *
 */
public class NakazaAuthenticatedWebSession extends AuthenticatedWebSession {
    private Roles actualRoles;
    private Integer loggedUserId;
    private NakazaUser nakazaUser;

    @SpringBean
    private UserService userService;

    /**
     * Construct.
     *
     * @param request
     *            The current request object
     */
    public NakazaAuthenticatedWebSession(Request request)
    {
        super(request);
        Injector.get().inject(this);
    }

    /**
     * @see org.apache.wicket.authroles.authentication.AuthenticatedWebSession#authenticate(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public boolean authenticate(final String username, final String password)
    {
        NakazaUser authenticated = userService.authenticate(username, Pwd.getSHA(password));

        if(authenticated != null){
            if(authenticated.getRole() == 1) {
                actualRoles = new Roles(new String[]{
                        NakazaRoles.USER.getRoleName()
                });
            } else if(authenticated.getRole() == 2) {
                actualRoles = new Roles(new String[]{
                        NakazaRoles.USER.getRoleName(),
                        NakazaRoles.EDITOR.getRoleName()
                });
            } else {
                actualRoles = new Roles(new String[]{
                        NakazaRoles.USER.getRoleName(),
                        NakazaRoles.ADMIN.getRoleName(),
                        NakazaRoles.EDITOR.getRoleName()
                });
            }

            loggedUserId = authenticated.getId();
            nakazaUser = authenticated;
            return true;
        } else {
            return false;
        }
    }

    public NakazaUser getLoggedUser() {
        if(!isSignedIn()){
            return null;
        }
        return nakazaUser;
    }

    /**
     * @see org.apache.wicket.authroles.authentication.AuthenticatedWebSession#getRoles()
     */
    @Override
    public Roles getRoles()
    {
        if (isSignedIn())
        {
            // If the user is signed in, they have these roles
            return actualRoles;
        }
        return null;
    }
}
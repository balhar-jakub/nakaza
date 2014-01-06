Jméno:        Jakub Balhar
Osobní Číslo: A18N0180P
Orion Login:  balhar
Email:        balhar.jakub@gmail.com

URL: nakaza.larpovadatabaze.cz
Pro přihlášení jako admin a tedy správu existujících příběhů je možné se přihlásit emailem balhar.jakub@gmail.com a heslem 5e5icha
Aplikace je odevzdávána pomocí SVN.

Zprovoznění a spuštění pomocí příkazu: ant -Dorion=balhar -Ddevel=pia -Dmaven=true funguje bez problému.
Zprovoznění pomocí příkazu: ant -Dorion=balhar -Dmaven=true z svn data stáhne, ale stáhne je do adresáře /tmp/pia/balhar/aplikace zatímco build proces je očekává v /tmp/pia/balhar/tags/aplikace takže se na tom build proces zasekne a aplikace se nespustí.
V případě spuštění pomocí příkazu ant -Dorion=balhar se stáhnou data a proces se zasekne, jelikož nezjistí, že jde o maven.

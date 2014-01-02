dropdb -U pia balhar
createdb -U pia -O pia balhar

psql -U pia -d balhar -f nakaza20140102.sql
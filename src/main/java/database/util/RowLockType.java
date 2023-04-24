package database.util;

public enum RowLockType {

    NONE,
    FOR_SHARE,
    FOR_UPDATE;

    /**
     * Appends a lock to the end of the query.
     * @param query the query you want to append the lock to.
     * @return the query with the lock appended to it.
     */
    public String getQueryWithLock(String query) {
        return switch (this) {
            case FOR_SHARE -> query + " LOCK IN SHARE MODE";
            case FOR_UPDATE -> query + " FOR UPDATE";
            default -> query;
        };
    }
}

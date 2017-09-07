package ua.nure.patsera.periodicals.exceptions;

/**
 * Created by Дарина on 03.09.2017.
 */
public class GenericDaoException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param  reason    the detail message. The detail message is saved for
     *         later retrieval by the {@link #getMessage()} method.
     */
    public GenericDaoException(String reason) { super(reason); }

    /**
     * Constructs a new runtime exception connect with SQL queries with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param reason    the detail message (which is saved for later retrieval
     *                  by the {@link #getMessage()} method).
     * @param e         the cause (which is saved for later retrieval by the
     *                  {@link #getCause()} method).  (A <tt>null</tt> value is
     *                  permitted, and indicates that the cause is nonexistent or
     *                  unknown.)
     */
    public GenericDaoException(String reason, Exception e) { super(reason, e); }
}

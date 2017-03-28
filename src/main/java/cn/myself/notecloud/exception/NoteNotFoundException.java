package cn.myself.notecloud.exception;

public class NoteNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5649196111046489471L;

	public NoteNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

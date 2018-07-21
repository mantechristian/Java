class BadTransactionException extends Exception
{
	public BadTransactionException(int amt)
	{
		super("Error:  Tried to deposit less than 0: " + amt);
	}
}
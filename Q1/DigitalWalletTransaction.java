public class DigitalWalletTransaction {

	public void addMoney(DigitalWallet digitalWallet, int amount) throws TransactionException{
		if (digitalWallet.getUserAccessToken() == null) {
			throw new TransactionException("USER_NOT_AUTHORIZED", "User not authorized");
		}
		if (amount < 1) {
			throw new TransactionException("INVALID_AMOUNT", "Amount should be greater than zero");
		}
		digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() + amount);
	}
	
	public void payMoney(DigitalWallet digitalWallet, int amount) throws TransactionException{
		if (digitalWallet.getUserAccessToken() == null) {
			throw new TransactionException("USER_NOT_AUTHORIZED", "User not authorized");
		}
		if (amount < 1) {
			throw new TransactionException("INVALID_AMOUNT", "Amount should be greater than zero");
		}
		if(digitalWallet.getWalletBalance() < amount) {
			throw new TransactionException("INSUFFICIENT_BALANCE", "Insufficient balance");
		}
		digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() - amount);
	}
}

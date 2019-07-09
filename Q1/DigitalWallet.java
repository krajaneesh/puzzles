public class DigitalWallet {

	private String walletId;
	private String userName;
	private String userAccessCode;	
	private int walletBalance;

	public DigitalWallet(String walletId, String userName) {
		this.walletId = walletId;
		this.userName = userName;
	}

	DigitalWallet(String walletId, String userName, String userAccessCode) {
		this.walletId = walletId;
		this.userName = userName;
		this.userAccessCode = userAccessCode;
	}
	
	public int getWalletBalance() {
		return walletBalance;
	}
	
	public void setWalletBalance(int balance) {
		this.walletBalance = balance;
	}
	
	public String getWalletId() {
		return walletId;
	}
	
	public String getUsername() {
		return userName;
	} 
	
	public String getUserAccessToken() {
		return userAccessCode;
	}
}

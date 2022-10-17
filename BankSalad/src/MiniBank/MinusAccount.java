package MiniBank;
// 계좌클래스를 상속받는 마이너스 계좌
public class MinusAccount extends Account {

	// 마이너스 한도금
	int MinusLimit;
	
	// 생성자
	public MinusAccount(String name, String accountNumber, int balance, int minusLimit) 
	{
		// 상위 클래스에 생성자 전달
		super(name, accountNumber, balance);
		// 마이너스 한도액 설정
		MinusLimit = minusLimit;
	}
}

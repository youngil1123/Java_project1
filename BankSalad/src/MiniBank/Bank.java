package MiniBank;
import java.util.ArrayList;
import java.util.Scanner;

// Bank 인터페이스를 구현하는 Bank 클래스
public class Bank implements BankInterface {

	// 은행의 전체 잔액
	int Balance;
	// 계좌리스트
	ArrayList<Account> AccList;
	
	// 생성자
	public Bank()
	{
		AccList = new ArrayList<Account>();
		Balance = 10000000; // 초기 지급준비금
	}
	
	// 계좌생성 인터페이스 구현
	@Override
	public void MakeAccount() {
		
		Scanner in = new Scanner(System.in);
		
		// 기본정보 입력
		System.out.print("예금주 : ");
		String name = in.nextLine();
		
		System.out.print("계좌번호 : ");
		String AccNo = in.nextLine();
		
		System.out.print("초기입금액 : ");
		int balance = in.nextInt();
		
		// 계좌정보리스트에 추가
		Account acc = new Account(name, AccNo, balance);
		AccList.add(acc);
		
		// 은행잔고 추가
		Balance += balance;
	}

	// 마이너스계좌 생성 인터페이스 구현
	@Override
	public void MakeMinusAccount() {
		Scanner in = new Scanner(System.in);
		
		// 기본정보 입력
		System.out.print("예금주 : ");
		String name = in.nextLine();
		
		System.out.print("계좌번호 : ");
		String AccNo = in.nextLine();
		
		System.out.print("초기입금액 : ");
		int balance = in.nextInt();
		
		// 마이너스 계좌이므로 마이너스 한도 입력
		System.out.print("마이너스 한도 : ");
		int minusLimit = in.nextInt();
		
		// 객체를 생성하여 계좌 리스트에 추가
		MinusAccount acc = new MinusAccount(name, AccNo, balance, minusLimit);
		AccList.add(acc);
		
		// 은행잔고 추가
		Balance += balance;
	}

	// 계좌 해지 인터페이스 구현
	@Override
	public void DeleteAccount() {
		Scanner in = new Scanner(System.in);
		
		// 해지 계좌정보
		System.out.print("해지하실 계좌번호를 입력해주세요 : ");
		String accNumber = in.nextLine();
		
		boolean isFind = false;
		for(Account acc : AccList) // 계좌를 찾는다.
		{
			if(acc.AccountNumber.equals(accNumber)) // 찾았으면 해지
			{
				AccList.remove(acc);
				isFind = true;
				break;
			}
		}
		
		// 찾은경우
		if(isFind)
		{
			System.out.println("해지되었습니다.");
		}
		else // 못찾은 경우
		{
			System.out.println("없는 계좌번호 입니다.");
		}
	}
	
	// 계좌번호 리스트 반환
	public ArrayList<Account> GetAccList()
	{
		return AccList;
	}
	
	// 은행전체 잔고에 추가
	public void AddTotalBalance(int amount)
	{
		Balance += amount;
	}
	
	// 특정 금액을 지급할 수 있는지를 반환
	public boolean IsMinusPossible(int amount)
	{
		if(Balance - amount < 0)
		{
			System.out.println("은행 잔고가 부족해 출금할 수 없습니다.");
			return false;
		}
		else
		{	
			return true;
		}
	}
	
	// 은행의 잔고에서 출금
	public void MinusTotalBalance(int amount)
	{
		Balance -= amount;
	}
	
}

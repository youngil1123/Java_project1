package MiniBank;

// ���� �������̽��� �����ϴ� ����Ŭ����
public class Account implements AccountOperationInterface {

	// ������
	String Name;
	// ���¹�ȣ
	String AccountNumber;
	// �ܾ�
	int Balance;
	
	// ������
	public Account(String name, String accountNumber, int balance)
	{
		Name = name;
		AccountNumber = accountNumber;
		Balance = balance;
	}
	
	// ���� �������̽� ����
	@Override
	public void Deposit(int amount) {
		
		Balance += amount;
	}

	// ��� �������̽� ����
	@Override
	public void Withdraw(int amount) {
		Balance -= amount;
	}

	// �۱� �������̽� ����
	@Override
	public void Transfer(int amount) {
		Balance -= amount;
	}
}

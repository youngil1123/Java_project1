package MiniBank;
// ����Ŭ������ ��ӹ޴� ���̳ʽ� ����
public class MinusAccount extends Account {

	// ���̳ʽ� �ѵ���
	int MinusLimit;
	
	// ������
	public MinusAccount(String name, String accountNumber, int balance, int minusLimit) 
	{
		// ���� Ŭ������ ������ ����
		super(name, accountNumber, balance);
		// ���̳ʽ� �ѵ��� ����
		MinusLimit = minusLimit;
	}
}

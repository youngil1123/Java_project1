package MiniBank;
import java.util.ArrayList;
import java.util.Scanner;

// Bank �������̽��� �����ϴ� Bank Ŭ����
public class Bank implements BankInterface {

	// ������ ��ü �ܾ�
	int Balance;
	// ���¸���Ʈ
	ArrayList<Account> AccList;
	
	// ������
	public Bank()
	{
		AccList = new ArrayList<Account>();
		Balance = 10000000; // �ʱ� �����غ��
	}
	
	// ���»��� �������̽� ����
	@Override
	public void MakeAccount() {
		
		Scanner in = new Scanner(System.in);
		
		// �⺻���� �Է�
		System.out.print("������ : ");
		String name = in.nextLine();
		
		System.out.print("���¹�ȣ : ");
		String AccNo = in.nextLine();
		
		System.out.print("�ʱ��Աݾ� : ");
		int balance = in.nextInt();
		
		// ������������Ʈ�� �߰�
		Account acc = new Account(name, AccNo, balance);
		AccList.add(acc);
		
		// �����ܰ� �߰�
		Balance += balance;
	}

	// ���̳ʽ����� ���� �������̽� ����
	@Override
	public void MakeMinusAccount() {
		Scanner in = new Scanner(System.in);
		
		// �⺻���� �Է�
		System.out.print("������ : ");
		String name = in.nextLine();
		
		System.out.print("���¹�ȣ : ");
		String AccNo = in.nextLine();
		
		System.out.print("�ʱ��Աݾ� : ");
		int balance = in.nextInt();
		
		// ���̳ʽ� �����̹Ƿ� ���̳ʽ� �ѵ� �Է�
		System.out.print("���̳ʽ� �ѵ� : ");
		int minusLimit = in.nextInt();
		
		// ��ü�� �����Ͽ� ���� ����Ʈ�� �߰�
		MinusAccount acc = new MinusAccount(name, AccNo, balance, minusLimit);
		AccList.add(acc);
		
		// �����ܰ� �߰�
		Balance += balance;
	}

	// ���� ���� �������̽� ����
	@Override
	public void DeleteAccount() {
		Scanner in = new Scanner(System.in);
		
		// ���� ��������
		System.out.print("�����Ͻ� ���¹�ȣ�� �Է����ּ��� : ");
		String accNumber = in.nextLine();
		
		boolean isFind = false;
		for(Account acc : AccList) // ���¸� ã�´�.
		{
			if(acc.AccountNumber.equals(accNumber)) // ã������ ����
			{
				AccList.remove(acc);
				isFind = true;
				break;
			}
		}
		
		// ã�����
		if(isFind)
		{
			System.out.println("�����Ǿ����ϴ�.");
		}
		else // ��ã�� ���
		{
			System.out.println("���� ���¹�ȣ �Դϴ�.");
		}
	}
	
	// ���¹�ȣ ����Ʈ ��ȯ
	public ArrayList<Account> GetAccList()
	{
		return AccList;
	}
	
	// ������ü �ܰ� �߰�
	public void AddTotalBalance(int amount)
	{
		Balance += amount;
	}
	
	// Ư�� �ݾ��� ������ �� �ִ����� ��ȯ
	public boolean IsMinusPossible(int amount)
	{
		if(Balance - amount < 0)
		{
			System.out.println("���� �ܰ� ������ ����� �� �����ϴ�.");
			return false;
		}
		else
		{	
			return true;
		}
	}
	
	// ������ �ܰ��� ���
	public void MinusTotalBalance(int amount)
	{
		Balance -= amount;
	}
	
}

package MiniBank;

// �������� �������̽�
public interface AccountOperationInterface {
	// ����
	abstract public void Deposit(int amount);
	// ���
	abstract public void Withdraw(int amount);
	// �۱�
	abstract public void Transfer(int amount);
}

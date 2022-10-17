package MiniBank;
import java.util.Scanner;

public class BankSystem {

	public static void main(String[] args) {
		// ���� ����
		Bank bank = new Bank();
		
		Scanner in = new Scanner(System.in);
		
		// �����Ҷ����� ����Ѵ�.
		while(true)
		{
			// �޴� ���
			System.out.println("�����ܰ� : " + bank.Balance);
			System.out.print("1.���� ����     ");
			System.out.print("2.���̳ʽ� ���� ����     ");
			System.out.print("3.���� ����     ");
			System.out.print("4.�Ա�     ");
			System.out.print("5.���     ");
			System.out.print("6.�۱�     ");
			System.out.println("7.����");
			System.out.print("�޴����� : ");
			
			// �޴� �Է�
			int menu = in.nextInt();
			
			// ���Ḧ �Է��ϸ� ���α׷��� ������.
			if(menu == 7)
			{
				System.out.println("�����մϴ�.");
				break;
			}
			
			// �߰�����
			int amount;
			String account;
			int i = 0;
			
			// �޴��� ����
			switch(menu)
			{
				case 1: // ���� ����
					bank.MakeAccount();
					break;
				case 2: // ���̳ʽ� ���� ����
					bank.MakeMinusAccount();
					break;
				case 3: // ���� ����
					bank.DeleteAccount();
					break;
				case 4: // �Ա�
					
					
					
					// �Աݾ�
					System.out.print("�Աݾ� : ");
					amount = in.nextInt();
					in.nextLine();
					
					// ���¹�ȣ
					System.out.print("���¹�ȣ : ");
					account = in.nextLine();
					
					// ���¹�ȣ�� �ִ��� ã�´�.
					for(i = 0; i < bank.GetAccList().size(); i++)
					{
						if(account.equals(bank.GetAccList().get(i).AccountNumber)) // ���¸� ã�Ҵٸ�
						{
							// �����ܰ�
							System.out.println("�����ܰ� : " + bank.GetAccList().get(i).Balance);
							
							// �ش� ���¿� �ܰ� �߰��ϰ�
							bank.GetAccList().get(i).Deposit(amount);
							// ������ ��ü�ܰ� �߰��Ѵ�.
							bank.AddTotalBalance(amount);
							
							// �ԱݿϷ�
							System.out.println("�ԱݵǾ����ϴ�. �ܾ� : " + bank.GetAccList().get(i).Balance);
							System.out.println("�����ܰ� : " + bank.Balance);
							
							break;
						}
					}
					
					// ���������� �߸� �Է��ϸ�
					if(i == bank.GetAccList().size())
					{
						System.out.println("���� �����Դϴ�.");
					}
					
					break;
					
				case 5: // ����ϱ�
					// ��ݾ�
					System.out.print("��ݾ� : ");
					amount = in.nextInt();
					in.nextLine();
					
					// ����� ���¹�ȣ
					System.out.print("���¹�ȣ : ");
					account = in.nextLine();
					
					if(bank.IsMinusPossible(amount)) // ���࿡�� ����� �������� ���� ������ �Ѵ�. ���࿡ �����ִµ��� ����Ҹ�ŭ �ִ��� Ȯ�� 
					{
						// ���¸� ã�´�.
						for(i = 0; i < bank.GetAccList().size(); i++)
						{
							if(account.equals(bank.GetAccList().get(i).AccountNumber)) // ���¸� ã�Ҵٸ�
							{
								// �ٽ� �� ���¿��� ����� ���������� Ȯ���ؾ� �Ѵ�.
								// ���̳ʽ� �����϶�
								if(bank.GetAccList().get(i) instanceof MinusAccount)
								{
									MinusAccount temp = (MinusAccount)bank.GetAccList().get(i);
									// ���̳ʽ� �ѵ��� �ʰ��ߴٸ�
									if((temp.Balance - amount) * -1 > temp.MinusLimit)
									{
										System.out.println("���̳ʽ� ���� �ѵ��ʰ��� ����� �Ұ����մϴ�.");
									}
									else // ��ݰ���
									{
										// �����ܰ�
										System.out.println("�����ܰ� : " + temp.Balance);
										
										temp.Withdraw(amount);
										bank.MinusTotalBalance(amount);
										System.out.println("��ݵǾ����ϴ�. �ܾ� : " + temp.Balance);
										System.out.println("�����ܰ� : " + bank.Balance);
									}
								}
								else // �Ϲݰ��¶��
								{
									Account temp = bank.GetAccList().get(i);
									
									// �ܾ� �ѵ��� �ʰ��ߴٸ�
									if(temp.Balance < amount)
									{
										System.out.println("�ܾ��� �����մϴ�.");
									}
									else // ��ݰ���
									{
										// �����ܰ�
										System.out.println("�����ܰ� : " + temp.Balance);
										
										temp.Withdraw(amount);
										bank.MinusTotalBalance(amount);
										System.out.println("��ݵǾ����ϴ�. �ܾ� : " + temp.Balance);
										System.out.println("�����ܰ� : " + bank.Balance);
									}
								}
								
								break;
								
							}
						}
						
						// ���¹�ȣ�� �߸� �Է��� ���
						if(i == bank.GetAccList().size())
						{
							System.out.println("���� �����Դϴ�.");
						}
					}
					
					break;
					
				case 6: // �۱��ϱ�
					
					// �۱ݾ�
					System.out.print("�۱ݾ� : ");
					amount = in.nextInt();
					in.nextLine();
					
					// �۱ݰ���
					System.out.print("�۱� ���¹�ȣ : ");
					String accountSend = in.nextLine();
					
					// ���Ű���
					System.out.print("���� ���¹�ȣ : ");
					String accountRecv = in.nextLine();
					
					// �߰��ʿ亯��
					Account tempAcc = null;
					MinusAccount tempMinusAcc = null;
					Account recvAcc = null;
					boolean isMinus = false;
					
					// ���¸� ã�´�.
					for(i = 0; i < bank.GetAccList().size(); i++)
					{
						if(accountSend.equals(bank.GetAccList().get(i).AccountNumber)) // �۱� ���¸� ã�Ҵٸ�
						{
							// ���̳ʽ� �����϶�
							if(bank.GetAccList().get(i) instanceof MinusAccount)
							{
								// ��ü�� �����Ѵ�.
								tempMinusAcc = (MinusAccount)bank.GetAccList().get(i);
								isMinus = true;
							}
							else // �Ϲݰ��¶��
							{
								tempAcc = bank.GetAccList().get(i);
							}
							
							break;
						}
					}
					
					// �۱ݰ��°� �߸��Ȱ��
					if(i == bank.GetAccList().size())
					{
						System.out.println("�۱ݰ��°� �߸��Ǿ����ϴ�.");
					}
					else // �۱ݰ��°� ����ζ�� ���Ű��µ� ��������� Ȯ���ؾ� �Ѵ�.
					{
						// ���Ű��¸� ã�´�.
						for(i = 0; i < bank.GetAccList().size(); i++)
						{
							if(accountRecv.equals(bank.GetAccList().get(i).AccountNumber)) // ���¸� ã�Ҵٸ�
							{
								// ��ü�� �����صд�.
								recvAcc = bank.GetAccList().get(i);
								break;
							}
						}
						
						// ���Ű��°� �߸��Ȱ��
						if(i == bank.GetAccList().size())
						{
							System.out.println("���Ű��°� �߸��Ǿ����ϴ�.");
						}
						else // �Ѵ� ���°� ������̸�, �۱ݰ��°� ���� ���� �ִ� ��Ȳ���� Ȯ���Ѵ�.
						{
							if(isMinus) // �۱ݰ��°� ���̳ʽ� �����ΰ��
							{
								// ���̳ʽ� �ѵ��� �ʰ��ߴٸ�
								if((tempMinusAcc.Balance - amount) * -1 > tempMinusAcc.MinusLimit)
								{
									System.out.println("���̳ʽ� ���� �ѵ��ʰ��� ����� �Ұ����մϴ�.");
								}
								else //�۱ݰ���
								{
									// �����ܰ�
									System.out.println("�����ܰ� : " + tempMinusAcc.Balance);
									
									// �۱ݰ��¿��� ����
									tempMinusAcc.Transfer(amount);
									// ��ݰ��¿� ���ϱ�
									recvAcc.Deposit(amount);
									System.out.println("�۱ݵǾ����ϴ�. �ܾ� : " + tempMinusAcc.Balance);
								}
							}
							else // ���̳ʽ� ���°� �ƴϸ�
							{
								// �ܾ� �ѵ��� �ʰ��ߴٸ�
								if(tempAcc.Balance < amount)
								{
									System.out.println("�ܾ��� �����մϴ�.");
								}
								else // �۱ݰ���
								{
									// �����ܰ�
									System.out.println("�����ܰ� : " + tempAcc.Balance);
									
									// �۱ݰ��¿��� ����
									tempAcc.Transfer(amount);
									// ��ݰ��¿� ���ϱ�
									recvAcc.Deposit(amount);
									System.out.println("��ݵǾ����ϴ�. �ܾ� : " + tempAcc.Balance);
								}
							}
						}
					}
					
					break;
				default:
					break;
			}
			
			System.out.println();
		}
		
		// �Է� scanner �ݱ�
		in.close();
	}
}

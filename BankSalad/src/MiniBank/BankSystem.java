package MiniBank;
import java.util.Scanner;

public class BankSystem {

	public static void main(String[] args) {
		// 은행 생성
		Bank bank = new Bank();
		
		Scanner in = new Scanner(System.in);
		
		// 종료할때까지 계속한다.
		while(true)
		{
			// 메뉴 출력
			System.out.println("은행잔고 : " + bank.Balance);
			System.out.print("1.계좌 생성     ");
			System.out.print("2.마이너스 계좌 생성     ");
			System.out.print("3.계좌 해지     ");
			System.out.print("4.입금     ");
			System.out.print("5.출금     ");
			System.out.print("6.송금     ");
			System.out.println("7.종료");
			System.out.print("메뉴선택 : ");
			
			// 메뉴 입력
			int menu = in.nextInt();
			
			// 종료를 입력하면 프로그램이 끝난다.
			if(menu == 7)
			{
				System.out.println("감사합니다.");
				break;
			}
			
			// 추가변수
			int amount;
			String account;
			int i = 0;
			
			// 메뉴에 따라서
			switch(menu)
			{
				case 1: // 계좌 생성
					bank.MakeAccount();
					break;
				case 2: // 마이너스 계좌 생성
					bank.MakeMinusAccount();
					break;
				case 3: // 계좌 해지
					bank.DeleteAccount();
					break;
				case 4: // 입금
					
					
					
					// 입금액
					System.out.print("입금액 : ");
					amount = in.nextInt();
					in.nextLine();
					
					// 계좌번호
					System.out.print("계좌번호 : ");
					account = in.nextLine();
					
					// 계좌번호가 있는지 찾는다.
					for(i = 0; i < bank.GetAccList().size(); i++)
					{
						if(account.equals(bank.GetAccList().get(i).AccountNumber)) // 계좌를 찾았다면
						{
							// 현재잔고
							System.out.println("현재잔고 : " + bank.GetAccList().get(i).Balance);
							
							// 해당 계좌에 잔고를 추가하고
							bank.GetAccList().get(i).Deposit(amount);
							// 은행의 전체잔고를 추가한다.
							bank.AddTotalBalance(amount);
							
							// 입금완료
							System.out.println("입금되었습니다. 잔액 : " + bank.GetAccList().get(i).Balance);
							System.out.println("은행잔고 : " + bank.Balance);
							
							break;
						}
					}
					
					// 계좌정보를 잘못 입력하면
					if(i == bank.GetAccList().size())
					{
						System.out.println("없는 계좌입니다.");
					}
					
					break;
					
				case 5: // 출금하기
					// 출금액
					System.out.print("출금액 : ");
					amount = in.nextInt();
					in.nextLine();
					
					// 출금할 계좌번호
					System.out.print("계좌번호 : ");
					account = in.nextLine();
					
					if(bank.IsMinusPossible(amount)) // 은행에서 출금이 가능한지 먼저 따져야 한다. 은행에 남아있는돈이 출금할만큼 있는지 확인 
					{
						// 계좌를 찾는다.
						for(i = 0; i < bank.GetAccList().size(); i++)
						{
							if(account.equals(bank.GetAccList().get(i).AccountNumber)) // 계좌를 찾았다면
							{
								// 다시 그 계좌에서 출금이 가능한지를 확인해야 한다.
								// 마이너스 계좌일때
								if(bank.GetAccList().get(i) instanceof MinusAccount)
								{
									MinusAccount temp = (MinusAccount)bank.GetAccList().get(i);
									// 마이너스 한도를 초과했다면
									if((temp.Balance - amount) * -1 > temp.MinusLimit)
									{
										System.out.println("마이너스 계좌 한도초과로 출금이 불가능합니다.");
									}
									else // 출금가능
									{
										// 현재잔고
										System.out.println("현재잔고 : " + temp.Balance);
										
										temp.Withdraw(amount);
										bank.MinusTotalBalance(amount);
										System.out.println("출금되었습니다. 잔액 : " + temp.Balance);
										System.out.println("은행잔고 : " + bank.Balance);
									}
								}
								else // 일반계좌라면
								{
									Account temp = bank.GetAccList().get(i);
									
									// 잔액 한도를 초과했다면
									if(temp.Balance < amount)
									{
										System.out.println("잔액이 부족합니다.");
									}
									else // 출금가능
									{
										// 현재잔고
										System.out.println("현재잔고 : " + temp.Balance);
										
										temp.Withdraw(amount);
										bank.MinusTotalBalance(amount);
										System.out.println("출금되었습니다. 잔액 : " + temp.Balance);
										System.out.println("은행잔고 : " + bank.Balance);
									}
								}
								
								break;
								
							}
						}
						
						// 계좌번호를 잘못 입력한 경우
						if(i == bank.GetAccList().size())
						{
							System.out.println("없는 계좌입니다.");
						}
					}
					
					break;
					
				case 6: // 송금하기
					
					// 송금액
					System.out.print("송금액 : ");
					amount = in.nextInt();
					in.nextLine();
					
					// 송금계좌
					System.out.print("송금 계좌번호 : ");
					String accountSend = in.nextLine();
					
					// 수신계좌
					System.out.print("수신 계좌번호 : ");
					String accountRecv = in.nextLine();
					
					// 추가필요변수
					Account tempAcc = null;
					MinusAccount tempMinusAcc = null;
					Account recvAcc = null;
					boolean isMinus = false;
					
					// 계좌를 찾는다.
					for(i = 0; i < bank.GetAccList().size(); i++)
					{
						if(accountSend.equals(bank.GetAccList().get(i).AccountNumber)) // 송금 계좌를 찾았다면
						{
							// 마이너스 계좌일때
							if(bank.GetAccList().get(i) instanceof MinusAccount)
							{
								// 객체를 저장한다.
								tempMinusAcc = (MinusAccount)bank.GetAccList().get(i);
								isMinus = true;
							}
							else // 일반계좌라면
							{
								tempAcc = bank.GetAccList().get(i);
							}
							
							break;
						}
					}
					
					// 송금계좌가 잘못된경우
					if(i == bank.GetAccList().size())
					{
						System.out.println("송금계좌가 잘못되었습니다.");
					}
					else // 송금계좌가 제대로라면 수신계좌도 제대로인지 확인해야 한다.
					{
						// 수신계좌를 찾는다.
						for(i = 0; i < bank.GetAccList().size(); i++)
						{
							if(accountRecv.equals(bank.GetAccList().get(i).AccountNumber)) // 계좌를 찾았다면
							{
								// 객체를 저장해둔다.
								recvAcc = bank.GetAccList().get(i);
								break;
							}
						}
						
						// 수신계좌가 잘못된경우
						if(i == bank.GetAccList().size())
						{
							System.out.println("수신계좌가 잘못되었습니다.");
						}
						else // 둘다 계좌가 제대로이면, 송금계좌가 돈을 뺄수 있는 상황인지 확인한다.
						{
							if(isMinus) // 송금계좌가 마이너스 계좌인경우
							{
								// 마이너스 한도를 초과했다면
								if((tempMinusAcc.Balance - amount) * -1 > tempMinusAcc.MinusLimit)
								{
									System.out.println("마이너스 계좌 한도초과로 출금이 불가능합니다.");
								}
								else //송금가능
								{
									// 현재잔고
									System.out.println("현재잔고 : " + tempMinusAcc.Balance);
									
									// 송금계좌에서 빼기
									tempMinusAcc.Transfer(amount);
									// 출금계좌에 더하기
									recvAcc.Deposit(amount);
									System.out.println("송금되었습니다. 잔액 : " + tempMinusAcc.Balance);
								}
							}
							else // 마이너스 계좌가 아니면
							{
								// 잔액 한도를 초과했다면
								if(tempAcc.Balance < amount)
								{
									System.out.println("잔액이 부족합니다.");
								}
								else // 송금가능
								{
									// 현재잔고
									System.out.println("현재잔고 : " + tempAcc.Balance);
									
									// 송금계좌에서 빼기
									tempAcc.Transfer(amount);
									// 출금계좌에 더하기
									recvAcc.Deposit(amount);
									System.out.println("출금되었습니다. 잔액 : " + tempAcc.Balance);
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
		
		// 입력 scanner 닫기
		in.close();
	}
}

class Account(var balance: Double) {

	def deposit(amount: Double): Unit = {
		require(amount > 0, "Deposit amount must be positive")
		balance += amount
	}

	def withdraw(amount: Double): Unit = {
		require(amount > 0, "Withdraw amount must be positive")
		if (amount <= balance) {
			balance -= amount
		} else {
			println("Insufficient balance")
		}
	}

	def transfer(amount: Double, targetAccount: Account): Unit = {
		require(amount > 0, "Transfer amount must be positive")
		if (amount <= balance) {
			this.withdraw(amount)
			targetAccount.deposit(amount)
			println(s"Transferred $amount to the target account.")
		} else {
			println("Insufficient balance to complete the transfer.")
		}
	}

	override def toString: String = s"Account balance: $balance"
}

object Bank {

	var accounts: List[Account] = List()

	def listNegativeBalances(): List[Account] = {
		accounts.filter(_.balance < 0)
	}

	def totalBalance(): Double = {
		accounts.map(_.balance).sum
	}

	def applyInterest(): Unit = {
		accounts.foreach { account =>
			if (account.balance > 0) {
				account.deposit(account.balance * 0.05)
			} else {
				account.withdraw(account.balance.abs * 0.1)
			}
		}
	}
}

object BankApp extends App {

	val acc1 = new Account(1000)
	val acc2 = new Account(-500)
	val acc3 = new Account(200)
	val acc4 = new Account(-1000)

	Bank.accounts = List(acc1, acc2, acc3, acc4)

	println("Initial Account Balances:")
	Bank.accounts.foreach(println)

	println("\nAccounts with Negative Balances:")
	Bank.listNegativeBalances().foreach(println)

	val totalBalance = Bank.totalBalance()
	println(s"\nTotal balance of all accounts: $totalBalance}")

	Bank.applyInterest()
	println("\nFinal Account Balances after applying interest:")
	Bank.accounts.foreach(println)
}

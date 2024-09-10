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

object AccountApp extends App {
  val acc1 = new Account(1000) 
  val acc2 = new Account(500)
  println(acc1)
  println(acc2)

  acc1.deposit(200)
  println(acc1)

  acc1.withdraw(300)
  println(acc1)

  acc1.transfer(400, acc2)
  println(acc1)
  println(acc2)
}

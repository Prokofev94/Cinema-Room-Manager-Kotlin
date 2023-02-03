package cinema

var purchasedTickets = 0
var percentage = "0.00%"
var currentIncome = 0
var totalIncome = 0

fun main() {
    val room = createRoom()
    while (true) {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        when (readln()) {
            "1" -> showRoom(room)
            "2" -> buyTicket(room)
            "3" -> showStatistics()
            "0" -> break
        }
    }
}

fun createRoom(): MutableList<MutableList<Char>> {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
    totalIncome = rows * seats * 10
    if (rows * seats > 60) totalIncome -= (rows - rows / 2) * 2 * seats
    return  MutableList(rows) { MutableList(seats) { 'S' } }
}

fun showRoom(room: MutableList<MutableList<Char>>) {
    print("\nCinema:\n ")
    for (i in 1..room[0].size) {
        print(" $i")
    }
    for (i in 1..room.size) {
        print("\n$i")
        for (j in room[0].indices) {
            print(" ${room[i - 1][j]}")
        }
    }
    println()
}

fun buyTicket(room: MutableList<MutableList<Char>>) {
    println("\nEnter a row number:")
    val row = readln().toInt()
    println("Enter a seat number in that row:")
    val seat = readln().toInt()
    println()

    if (row - 1 !in room.indices || seat - 1 !in room[0].indices) {
        println("Wrong input!\n")
        buyTicket(room)
    } else if (room[row - 1][seat - 1] == 'B') {
        println("That ticket has already been purchased!\n")
        buyTicket(room)
    } else {
        room[row - 1][seat - 1] = 'B'
        val totalSeats = room.size * room[0].size
        val price = if (totalSeats > 60 && row > room.size / 2) 8 else 10
        println("Ticket price: $$price")
        purchasedTickets++
        percentage = "%.2f%%".format(100.0 / room.size / room[0].size * purchasedTickets)
        currentIncome += price
    }
}

fun showStatistics() {
    println("\nNumber of purchased tickets: $purchasedTickets")
    println("Percentage: $percentage")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
}
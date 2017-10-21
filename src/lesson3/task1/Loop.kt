@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    return if (n == 0) 1 else {
        var result = 0
        var k = n
        while (k != 0) {
            k /= 10
            result++
        }
        return result
    }
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int =
        if (n <= 2) 1 else ((Math.pow(((Math.sqrt(5.0) + 1) / 2), n.toDouble()) / Math.sqrt(5.0)) + 0.5).toInt()

//Нахождение Наибольшего Общего делителя//
fun nod(a: Int, b: Int): Int {
    var k = a
    var l = b
    while (k != 0 && l != 0) {
        if (k > l)
            k %= l
        else
            l %= k
    }
    return (k + l)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = m * n / nod(m, n)

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var l = 2
    if (n == l) return n
    else for (i in 2..n / 2) {
        if (n % i == 0) break
        l++
    }
    return if (l > n / 2) n else l
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var l = n / 2
    for (i in n / 2 downTo 1) {
        if (n % i == 0) break
        l--
    }
    return l
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val l = nod(m, n)
    return (l == 1)
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (k in Math.sqrt(m.toDouble()).toInt()..Math.sqrt(n.toDouble()).toInt()) {
        if (sqr(k.toDouble()) >= m && sqr(k.toDouble()) <= n) return true
    }
    return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var n = x
    while (Math.abs(n) > 2 * Math.PI) {
        n -= 2 * Math.PI
    }
    var l = 0.0
    var count1 = 2.0
    var count2 = 1.0
    do {
        val part1 = Math.pow(-1.0, count1) * Math.pow(n, count2) / factorial(count2.toInt())
        l += part1
        count1++
        count2 += 2
    } while (Math.abs(part1) >= eps)
    return l
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var n = x
    while (Math.abs(n) > 2 * Math.PI) {
        n -= 2 * Math.PI
    }
    var l = 0.0
    var count1 = 2.0
    var count2 = 0.0
    do {
        val part1 = Math.pow(-1.0, count1) * Math.pow(n, count2) / factorial(count2.toInt())
        l += part1
        count1++
        count2 += 2
    } while (Math.abs(part1) >= eps)
    return l
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    val digNubm = digitNumber(n)
    var result = 0
    var degree = 1
    for (i in digNubm - 1 downTo 0) {
        result += (n / Math.pow(10.0, i.toDouble()).toInt()) % 10 * degree
        degree *= 10
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    val digNubm = digitNumber(n)
    var result = 0
    var degree = 1
    for (i in digNubm - 1 downTo 0) {
        result += (n / Math.pow(10.0, i.toDouble()).toInt()) % 10 * degree
        degree *= 10
    }
    return n == result
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val digNumb = digitNumber(n)
    val k = n % 10
    for (i in 1 until digNumb) {
        if (n / Math.pow(10.0, i.toDouble()).toInt() % 10 != k) return true
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var k = 0.0
    var count = 0.0
    while (n > k) {
        count++
        k += digitNumber(sqr(count).toInt())
    }
    return (sqr(count) / Math.pow(10.0, (k - n))).toInt() % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var k = 0.0
    var count = 0
    while (n > k) {
        count++
        k += digitNumber(fib(count))
    }
    return (fib(count) / Math.pow(10.0, (k - n))).toInt() % 10
}

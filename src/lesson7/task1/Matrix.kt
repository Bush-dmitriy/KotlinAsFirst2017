@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height > 0 && width > 0) {
        val result = MatrixImpl<E>(height, width)
        for (i in 0 until height)
            for (j in 0 until width)
                result[i, j] = e
        return result
    } else throw IllegalArgumentException()
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int) : Matrix<E> {

    private val map = mutableMapOf<Cell, E>()

    override fun get(row: Int, column: Int): E = map[Cell(row, column)] ?: throw IllegalArgumentException()

    override fun get(cell: Cell): E = map[cell] ?: throw IllegalArgumentException()

    override fun set(row: Int, column: Int, value: E) {
        map[Cell(row, column)] = value
    }

    override fun set(cell: Cell, value: E) {
        map[cell] = value
    }

    override fun equals(other: Any?) = other is MatrixImpl<*> &&
            height == other.height &&
            width == other.width

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + map.hashCode()
        return result
    }

    override fun toString(): String {
        val matrixString = StringBuilder()
        matrixString.append('[')
        for (row in 0 until height) {
            matrixString.append('[')
            for (column in 0 until width - 1) {
                matrixString.append(this[row, column]).append(", ")
            }
            matrixString.append(this[row, width]).append(']')
            if (row != height-1) matrixString.append(", ")
        }
        matrixString.append(']')
        return matrixString.toString()
    }
}

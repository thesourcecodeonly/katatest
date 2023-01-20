package calculator;

import java.util.Scanner;

public class Calc {

    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        int arabian;
        String result;
        boolean isRomanish;
        // из введенной строки делаем массив строк, разделитель - пробел
        String[] operands = expression.split(" ");
        // второй элемент массива присваиваем переменной operator
        String operator = operands.length == 3 ? operands[1] : null;
        // если введена некорректная строка (в массиве operands меньше трех элементов) - исключение
        if (operands.length < 3) throw new Exception("Строка не является математической операцией");
        // если формат матем.строки не удовлетворяет заданию - исключение
        if (operands.length > 3 || !isCorrectOperator(operands[1])) throw new Exception("Формат математической операции не удовлетворяет заданию - " +
                " два операнда и один оператор (+, -, /, *)");
        // если оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[2])) {
            // конвертируем в арабские
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[2]);
            isRomanish = true;
        }
        // если оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[2])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[2]);
            isRomanish = false;
        }
        // если одно число - римское, другое - арабское - исключение
        else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        // в переменную arabian записываем результат вызова метода calc()
        arabian = calc(num1, num2, operator);
        // получаем значение переменной result, в зависимости от системы счисления
        if (isRomanish) {
            // если результат вычисления римских чисел меньше или равно нулю - исключение
            if (arabian <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            // конвертируем результат вычисления из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else {
            // конвертируем арабское число в String
            result = String.valueOf(arabian);
        }
        return result;
    }

    public static boolean isCorrectOperator(String operator) {
        if (operator.contains("+") || operator.contains("-")
                || operator.contains("*") || operator.contains("/")) {
            return true;
        }
        return false;
    }

    public static int calc(int a, int b, String operator) {
        if (operator.equals("+")) return a + b;
        else if (operator.equals("-")) return a - b;
        else if (operator.equals("*")) return a * b;
        else return a / b;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два римских или два арабских числа от 1 до 10: ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }
}


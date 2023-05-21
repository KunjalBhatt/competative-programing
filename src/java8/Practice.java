package java8;

import java8.pojo.Department;
import java8.pojo.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Practice {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse("2020-01-35", dtf);
        System.out.println(ld+" "+ld.getMonth());



        List<Employee> ls = new ArrayList<>();

        ls.add(new Employee(1,"Shivika", Department.RD, 500d));
        ls.add(new Employee(2,"Kunjal", Department.HR,1254d));
        ls.add(new Employee(3,"Dipa", Department.RD,6324d));
        ls.add(new Employee(4,"Digesh", Department.HR,4512d));
        ls.add(new Employee(5,"Apurva", Department.RD,41214d));
        ls.add(new Employee(6,"Raman", Department.HR,85452d));
        ls.add(new Employee(7,"Priyavadan", Department.HR,251126d));
        ls.add(new Employee(8,"Sadguna", Department.RD,52114d));

        Map<Integer, Employee> map = ls.stream().collect(Collectors.toMap(Employee::getId,Function.identity()));

        System.out.println(map);


        Map<Department, Employee> res = ls.stream().
                collect(
                        Collectors.toMap(
                                Employee::getDepartment,
                                i -> i,
                                BinaryOperator.maxBy(Comparator.comparingDouble(Employee::getSalary))
                        )
                );


        System.out.println(res);
        Map<Department, String> res1 = ls.stream().
                collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                                        i -> i.isPresent() ? i.get().getName() : "else"
                                )
                        )
                );
        System.out.println(res1);

        String[] sarr = "Kunjal.bhatt.hbasdf".split("\\.");

        int left=0;
        int end = sarr.length -1;

        while(left < end){
            String temp = sarr[end];
            sarr[end] =sarr[left];
            sarr[left] = temp;

            left++;
            end--;
        }

        Arrays.stream(sarr).forEach(System.out::println);

        System.out.println(Arrays.stream(sarr).collect(Collectors.joining(".")));


        List<Integer[]> listArr = new ArrayList<>();

        Integer[] p = new Integer[2];
        p[0] = 1;
        p[1] = 2;
        listArr.add(p);

        p = new Integer[2];
        p[0] = 2;
        p[1] = 3;
        listArr.add(p);

        p = new Integer[2];
        p[0] = 4;
        p[1] = 1;
        listArr.add(p);

        int[][] arr2 = listArr.stream()
                .map(integers -> Arrays.stream(integers).mapToInt(Integer::valueOf).toArray()).toArray(int[][]::new);

        System.out.println(Arrays.deepToString(arr2));

        List<Player> players = new ArrayList<>();
        Map<Player, Integer> playerCount = players.stream().filter(Player::isActive).collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum)));

    }






}

class Player{

    boolean isActive(){
        return true;
    }
}

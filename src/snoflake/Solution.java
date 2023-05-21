package snoflake;

import javax.management.MBeanRegistration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Solution {

    static PrintWriter pw;

    static Map<String, LinkedHashMap<String, Integer>> stateManufactureList  = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {





        List<Sales> listOfSales = new ArrayList<>();
        List<Product> listOfProducts = new ArrayList<>();
        List<Customer> listOfCustomers = new ArrayList<>();

        readData(listOfSales,listOfProducts,listOfCustomers);

        listOfSales.stream().map(i-> i.full.charAt(i.full.length() - 1)).distinct().sorted().collect(Collectors.toList()).forEach(System.out::println);

        /*

        listOfCustomers.stream().map(i->i.segment).collect(Collectors.toSet()).forEach(System.out::println);

        distributeManufactureData(listOfSales,listOfProducts);



        distributeToNstate(listOfSales,3,"Crumbly Confections Co.",listOfCustomers);
        distributeToNstate(listOfSales,2,"Pecan Pie Bakery",listOfCustomers);
        distributeToNstate(listOfSales,3,"Ginger Snap Treats",listOfCustomers);

        distributeAccount(listOfSales);

        */

     //   customerSegmentDistributionMonthWise(listOfSales);


      //  printInCsv("competative-distribution-sales",listOfSales);


    }


    private static void customerSegmentDistributionMonthWise(List<Sales> listOfSales){

        Map<Month, Map<String, Float>> monthWiseCustomerSegment = new HashMap<>();
        monthWiseCustomerSegment.put(Month.JANUARY, new HashMap<>());
        monthWiseCustomerSegment.put(Month.FEBRUARY, new HashMap<>());
        monthWiseCustomerSegment.put(Month.MARCH, new HashMap<>());
        monthWiseCustomerSegment.put(Month.APRIL, new HashMap<>());
        monthWiseCustomerSegment.put(Month.MAY, new HashMap<>());
        monthWiseCustomerSegment.put(Month.JUNE, new HashMap<>());

        Map<String,Float> segmentData = monthWiseCustomerSegment.get(Month.JANUARY);
        segmentData.put("OLDER SINGLES/COUPLES", 0f);
        segmentData.put("MIDAGE SINGLES/COUPLES", 0f);
        segmentData.put("RETIREES", 0f);
        segmentData.put("OLDER FAMILIES", 0f);
        segmentData.put("YOUNG SINGLES/COUPLES", 0f);
        segmentData.put("NEW FAMILIES", 0f);
        segmentData.put("YOUNG FAMILIES", 0f);

        segmentData = monthWiseCustomerSegment.get(Month.FEBRUARY);
        segmentData.put("OLDER SINGLES/COUPLES", 0f);
        segmentData.put("MIDAGE SINGLES/COUPLES", 0f);
        segmentData.put("RETIREES", 0f);
        segmentData.put("OLDER FAMILIES", 0f);
        segmentData.put("YOUNG SINGLES/COUPLES", 0f);
        segmentData.put("NEW FAMILIES", 0f);
        segmentData.put("YOUNG FAMILIES", 0f);

        segmentData = monthWiseCustomerSegment.get(Month.MARCH);
        segmentData.put("OLDER SINGLES/COUPLES", 0f);
        segmentData.put("MIDAGE SINGLES/COUPLES", 0f);
        segmentData.put("RETIREES", 0f);
        segmentData.put("OLDER FAMILIES", 0f);
        segmentData.put("YOUNG SINGLES/COUPLES", 0f);
        segmentData.put("NEW FAMILIES", 0f);
        segmentData.put("YOUNG FAMILIES", 0f);

        segmentData = monthWiseCustomerSegment.get(Month.APRIL);
        segmentData.put("OLDER SINGLES/COUPLES", 0f);
        segmentData.put("MIDAGE SINGLES/COUPLES", 0f);
        segmentData.put("RETIREES", 0f);
        segmentData.put("OLDER FAMILIES", 0f);
        segmentData.put("YOUNG SINGLES/COUPLES", 0f);
        segmentData.put("NEW FAMILIES", 0f);
        segmentData.put("YOUNG FAMILIES", 0f);

        segmentData = monthWiseCustomerSegment.get(Month.MAY);
        segmentData.put("OLDER SINGLES/COUPLES", 0f);
        segmentData.put("MIDAGE SINGLES/COUPLES", 0f);
        segmentData.put("RETIREES", 0f);
        segmentData.put("OLDER FAMILIES", 0f);
        segmentData.put("YOUNG SINGLES/COUPLES", 0f);
        segmentData.put("NEW FAMILIES", 0f);
        segmentData.put("YOUNG FAMILIES", 0f);

        segmentData = monthWiseCustomerSegment.get(Month.JUNE);
        segmentData.put("OLDER SINGLES/COUPLES", 0f);
        segmentData.put("MIDAGE SINGLES/COUPLES", 0f);
        segmentData.put("RETIREES", 0f);
        segmentData.put("OLDER FAMILIES", 0f);
        segmentData.put("YOUNG SINGLES/COUPLES", 0f);
        segmentData.put("NEW FAMILIES", 0f);
        segmentData.put("YOUNG FAMILIES", 0f);



        for(Map.Entry<Month, Map<String, Float>> monthMap : monthWiseCustomerSegment.entrySet()){

            Map<String, Float> segmentPer = monthMap.getValue();

            double totalSum = listOfSales.stream().filter(i->i.saleDate.getMonth().equals(monthMap.getKey())).mapToDouble(i->i.amount).sum();

            Map<String, Double> segmentReq = getRequired(totalSum, segmentPer);

            Map<String, Double> segmentOriginal = listOfSales.stream().filter(i->i.saleDate.getMonth().equals(monthMap.getKey()))
                    .collect(Collectors.groupingBy(i->i.getCustomer().segment,Collectors.summingDouble(i -> i.amount)));

            System.out.println("before Month : "+monthMap.getKey());
            calculatePer(segmentOriginal); // original per

            Queue<Sales> pool = new LinkedList<>();
            for(Map.Entry<String, Double> evOriginal : segmentOriginal.entrySet()){
                if(evOriginal.getValue() > segmentReq.get(evOriginal.getKey())){
                    double diff = evOriginal.getValue() - segmentReq.get(evOriginal.getKey());
                    List<Sales> moveList= listOfSales.stream().filter(i->i.getCustomer().segment.equals(evOriginal.getKey())).collect(Collectors.toList());
                    for(int i=0; i<moveList.size() && diff>=0;i++){
                        Sales s = moveList.get(i);
                        pool.add(s);
                        diff-=s.amount;
                    }

                }
            }


            for(Map.Entry<String, Double> evOriginal : segmentOriginal.entrySet()){
                if(evOriginal.getValue() < segmentReq.get(evOriginal.getKey())){
                    double diff = segmentReq.get(evOriginal.getKey()) - evOriginal.getValue();
                    List<Customer> customers = listOfSales.stream().map(Sales::getCustomer).filter(customer -> customer.segment.equals(evOriginal.getKey())).collect(Collectors.toList());
                    while( diff >= 0 ){
                        Random r = new Random();
                        Sales s = pool.poll();
                        assert s != null;
                        s.setCustomer(customers.get(r.nextInt(customers.size())));
                        diff-=s.amount;
                    }
                   
                }

            }

            System.out.println("After Month : "+monthMap.getKey());
            calculatePer(listOfSales.stream().filter(i->i.saleDate.getMonth().equals(monthMap.getKey()))
                    .collect(Collectors.groupingBy(i->i.getCustomer().segment,Collectors.summingDouble(i -> i.amount)))); // original per


        }

    }

    private static Map<String, Double> getRequired(double totalSum, Map<String, Float> segmentPer) {

        Map<String, Double> required = new HashMap<>();

        for (Map.Entry<String, Float> ev : segmentPer.entrySet()){
            required.put(ev.getKey(), (ev.getValue() * 100) / totalSum );
        }

        return required;
    }

    private static void distributeAccount(List<Sales> listOfSales) {


        Map<String, Integer> accountDistribution = new HashMap<>();
        accountDistribution.put("Kroger",42);
        accountDistribution.put("Costco",25);
        accountDistribution.put("Dollar Tree``",18);
        accountDistribution.put("Walgreens",15);

        calculatePer(listOfSales.stream().collect(Collectors.groupingBy(i->i.account, Collectors.summingDouble(i-> 1))));


        Map<String,Double> before = getOriginalDistribution(listOfSales,accountDistribution);
        calculatePer(before);


        Map<String, Integer> requiredAccount = new HashMap<>();
        for(Map.Entry<String, Integer> ev : accountDistribution.entrySet()){
            Integer count = (ev.getValue()* listOfSales.size())/100;
            requiredAccount.put(ev.getKey(),count);
        }
        System.out.println(requiredAccount);
        Set<String> accountSet = listOfSales.stream().map(i->i.account).distinct().collect(Collectors.toSet());
        for(Sales s : listOfSales){
            Random r = new Random();
            String account = new ArrayList<>(accountSet).get(r.nextInt(accountSet.size()));

            if(!requiredAccount.containsKey(account)){
                System.out.println("-"+account+"-");
            }
            while(requiredAccount.get(account) < 0){
                accountSet.remove(account);
                account = new ArrayList<>(accountSet).get(r.nextInt(accountSet.size()));
            }

            if(requiredAccount.get(account) >= 0){
                s.setAccount(account);
                requiredAccount.put(account, requiredAccount.get(account)-1);
            }

        }
        calculatePer(listOfSales.stream().collect(Collectors.groupingBy(i->i.account, Collectors.summingDouble(i-> 1))));

    }

    static void distributeToNstate(List<Sales> listOfSales, int n, String manufacture, List<Customer> listOfCustomers){
        Map<String,List<Customer>> stateCustomer = listOfCustomers.stream().collect(Collectors.groupingBy(i->i.state,Collectors.toList()));
        List<String> states = listOfCustomers.stream().map(i->i.state).collect(Collectors.toList());
        Collections.shuffle(states);
        states = states.stream().limit(n).collect(Collectors.toList());

        List<Sales> manuSales = listOfSales.stream().filter(i->i.getProduct().manufacture.equals(manufacture)).collect(Collectors.toList());;

        for(Sales s : manuSales){
            Random r = new Random();
            String state = states.get(r.nextInt(states.size()));
            List<Customer> lc = stateCustomer.get(state);
            s.setCustomer(lc.get(r.nextInt(lc.size())));
        }

    }

    static void calculatePer(Map<String, Double> map){
        double total = 0;
        total = map.values().stream().mapToDouble(i->i).sum();

        for(Map.Entry<String, Double> ev : map.entrySet()){

            System.out.println(ev.getKey()+" = "+((ev.getValue()/total)*100));
        }

    }

    static void printData(Map<String,Long> before, Map<String,Long> after){

        try (PrintWriter out = new PrintWriter(new File("D://data//data-compare.csv"))) {

            for(Map.Entry<String, Long> ev : before.entrySet()){

                out.println(ev.getKey()+","+ev.getValue());
            }
            out.println();
            out.println();
            out.println();
            out.println();
            out.println();

            for(Map.Entry<String, Long> ev : after.entrySet()){

                out.println(ev.getKey()+","+ev.getValue());
            }

            out.flush();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    static void distributeManufactureData(List<Sales> listOfSales, List<Product> lsProduct){

        int suarDistribution = 31;
        int pepeerDistribution = 24;
        int heavenlyrDistribution = 22;
        int espressoDistribution = 21;


        Map<String, Integer> requiredD = new HashMap<>();
        requiredD.put("Sugar Rush Bakery",suarDistribution);
        requiredD.put("Peppermint Patty Co.",pepeerDistribution);
        requiredD.put("Heavenly Delights Treats",heavenlyrDistribution);
        requiredD.put("Espresso Escape Bakery",espressoDistribution);


        Map<String, Double> originalD = getOriginalDistribution(listOfSales, requiredD);
        calculatePer(originalD); // calculate current distribution

        double totalCount = listOfSales.stream().filter(i -> requiredD.containsKey(i.product.manufacture)).mapToDouble(i -> i.amount).sum();

        Queue<Sales> pool = new LinkedList<>();

        for(Map.Entry<String,Integer> rEv : requiredD.entrySet()){
            double requiredCount = (rEv.getValue() * totalCount) / 100;
            if(originalD.get(rEv.getKey()) >  requiredCount){
                double diff = originalD.get(rEv.getKey()) -  requiredCount;
                List<Sales> moveList = listOfSales.stream().filter(i->i.getProduct().manufacture.equals(rEv.getKey())).collect(Collectors.toList());
                for(int i=0; i<moveList.size() && diff > 0;i++){
                    pool.add(moveList.get(i));
                    diff-=moveList.get(i).amount;
                }
                System.out.println("Removed Data for = "+(originalD.get(rEv.getKey()) -  requiredCount));
            }
        }

        for(Map.Entry<String,Integer> rEv : requiredD.entrySet()){
            double requiredCount = (rEv.getValue() * totalCount) / 100;
            if(originalD.get(rEv.getKey()) <  requiredCount){
                double count = requiredCount - originalD.get(rEv.getKey());
                while(count >= 0 && !pool.isEmpty()){
                    Sales s =  pool.poll();
                    List<Product> mProducts = lsProduct.stream().filter(i->i.manufacture.equals(rEv.getKey())).collect(Collectors.toList());
                    Random r = new Random();
                    s.setProduct(mProducts.stream().skip(r.nextInt(mProducts.size())).findFirst().orElse(new Product(0,"","","")));
                    count-=s.amount;
                }
                System.out.println("Added Data for = "+rEv.getKey()+" = "+(requiredCount - originalD.get(rEv.getKey())));
            }
        }

        // left overs

        String maxMan = "";
        int maxCount = 0;

        for(Map.Entry<String,Integer> rEv : requiredD.entrySet()){
            if(rEv.getValue() > maxCount){
                maxMan = rEv.getKey();
                maxCount = rEv.getValue();
            }
        }

        while(!pool.isEmpty()){
            Sales s =  pool.poll();
            String finalMaxMan = maxMan;
            s.setProduct(lsProduct.stream().filter(i->i.manufacture.equals(finalMaxMan)).findAny().orElse(new Product(0,"","","")));
        }

        Map<String,Double> after = getOriginalDistribution(listOfSales,requiredD);
        calculatePer(after);

    }

    static Map<String,Double> getOriginalDistribution(List<Sales> list, Map<String,Integer> requiredD){


    //    System.out.println(list.size());
        Map<String,Double> data =  list.stream().filter(i->requiredD.containsKey(i.product.manufacture))
                .collect(Collectors.groupingBy(i->i.product.manufacture, Collectors.summingDouble(i->i.amount)));
    //    System.out.println(data);

        return data;
    }

    static void readData(List<Sales> listOfSales,List<Product> listOfProducts,List<Customer> listOfCustomers ) throws FileNotFoundException {
        File f = new File("D:\\data\\Sales_table_powerbi.csv");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (Scanner sc = new Scanner(new FileInputStream(f))) {
            sc.hasNextLine();sc.nextLine();
            while(sc.hasNextLine()){
                String fullString = sc.nextLine();
                String[] s = fullString.split(",");
                listOfSales.add(new Sales(s[0], Double.parseDouble(s[4]), LocalDate.parse(s[2], dtf) , Integer.parseInt(s[1]), Integer.parseInt(s[7]), fullString));

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        f = new File("D:\\data\\product-csv.csv");
        Map<Integer,Product> productMap= new HashMap<>();
        try (Scanner sc = new Scanner(new FileInputStream(f))) {
            sc.hasNextLine();sc.nextLine();
            while(sc.hasNextLine()){
                String[] s = sc.nextLine().split(",");
                Product p = new Product(Integer.valueOf(s[12]),s[9],s[10],s[8]);
                productMap.put(Integer.valueOf(s[12]), p);
                listOfProducts.add(p);
            }

        }

        f = new File("D:\\data\\Customer-data.csv");
        Map<Integer,Customer> customerMap= new HashMap<>();
        try (Scanner sc = new Scanner(new FileInputStream(f))) {
            sc.hasNextLine();sc.nextLine();
            while(sc.hasNextLine()){
                String[] s = sc.nextLine().split(",");
                Customer c =  new Customer(Integer.parseInt(s[0]),s[1], s[3].trim());
                customerMap.put(Integer.valueOf(s[0]),c);
                listOfCustomers.add(c);

            }

        }

        System.out.println(listOfSales.size());
        System.out.println(productMap.size());
        System.out.println(customerMap.size());

        System.out.println("Collect Data done");

        for(Sales sales : listOfSales){
            sales.setProduct(Optional.ofNullable(productMap.get(sales.productid)).orElse(new Product(sales.productid,"false","falsesuper","falseManufacture"+sales.productid)));
            sales.setCustomer(customerMap.get(sales.customer_id));

        }

    }


    static void printData(String brand, Map<String, Long> countMap){
        for(Map.Entry<String, Long> ev : countMap.entrySet()){
            pw.println(brand+","+ev.getKey()+","+ev.getValue());
        }
    }

    static void printInCsv(String fileName, List<Sales> sales){
        try (PrintWriter pout = new PrintWriter(new File("D://data//" + fileName + ".csv"))) {
            pout.println("ACCOUNT,PRODUCT_ID,TIME_DESCRIPTION,NO_OF_CUSTOMERS,DOLLARS_000S,UNITS_000S,KILOS_000S,CUSTOMER_ID,clusters");
            for(Sales s: sales){
                pout.println(s.full);
            }
            pout.flush();
        }catch (Exception e){
            e.printStackTrace();
        }



    }


}

class Sales{

    String account;
    int productid;
    int customer_id;

    LocalDate saleDate;

    double amount;

    Product product;
    Customer customer;

    String full;


    public Sales(String account, double amount, LocalDate date, int productid, int customer_id, String full) {
        this.account = account;
        this.productid = productid;
        this.customer_id = customer_id;
        this.amount = amount;
        this.saleDate = date;
        this.full = full;
    }

    public Product getProduct() {

        return product;
    }

    public void setProduct(Product product) {
        String[] col = full.split(",");
        col[1] = String.valueOf(product.productid);
        full = String.join(",", col);
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        String[] col = full.split(",");
        col[7] = String.valueOf(customer.customerid);
        full = String.join(",", col);
        this.customer = customer;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        String[] col = full.split(",");
        col[0] = account;
        full = String.join(",", col);
        this.account = account;
    }
}

class Product {

    int productid;

    String brand;
    String superBrand;

    String manufacture;

    public Product(int productid, String brand, String superBrand, String manufacture) {
        this.productid = productid;
        this.brand = brand;
        this.superBrand = superBrand;
        this.manufacture = manufacture;
    }
}

class Customer{

     int customerid;

     String segment;

     String state;

    public Customer(int customerid, String state, String segment) {
        this.customerid = customerid;
        this.state = state;
        this.segment = segment;
    }
}

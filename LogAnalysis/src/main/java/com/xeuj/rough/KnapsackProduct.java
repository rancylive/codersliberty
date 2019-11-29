package com.xeuj.rough;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProduct {

    static List<DeliveryProduct> my_pack;

    public static int fillPackage(double weight, ArrayList<DeliveryProduct> item, List<DeliveryProduct> optimalChoice, int n){
        //base case
        if(n == 0 || weight == 0)
            return 0;

        if(item.get(n-1).getTotalAmount() > weight) {
            List<DeliveryProduct> subOptimalChoice = new ArrayList<>();
            int optimalCost =fillPackage(weight, item, subOptimalChoice, n-1);
            optimalChoice.addAll(subOptimalChoice);
            return optimalCost;
        }
        else{
            List<DeliveryProduct> includeOptimalChoice = new ArrayList<>();
            List<DeliveryProduct> excludeOptimalChoice = new ArrayList<>();
            int include_cost = fillPackage((weight-item.get(n-1).getTotalAmount()), item, includeOptimalChoice, n-1);
            int exclude_cost = fillPackage(weight, item, excludeOptimalChoice, n-1);
            if(include_cost > exclude_cost){
                optimalChoice.addAll(includeOptimalChoice);
                optimalChoice.add(item.get(n - 1));
                return include_cost;
            }
            else{
                optimalChoice.addAll(excludeOptimalChoice);
                return exclude_cost;
            }
        }
    }

    public static void main(String args[]) {
        ArrayList<DeliveryProduct> itemList = new ArrayList<>();
        itemList.add(new DeliveryProduct("1",2));
        itemList.add(new DeliveryProduct("2",5));
        itemList.add(new DeliveryProduct("3",3));
        itemList.add(new DeliveryProduct("4",4));
        itemList.add(new DeliveryProduct("5",7));

        printOptimalChoice(itemList, 9);
        printOptimalChoice(itemList, 10);
        printOptimalChoice(itemList, 11);
    }

    private static void printOptimalChoice(ArrayList<DeliveryProduct> itemList, double weight) {
        my_pack = new ArrayList<>();
        fillPackage(weight, itemList, my_pack, itemList.size());
        System.out.println("Best choice for weight: " + weight);
        for(int i = 0; i < my_pack.size(); i++) {
            System.out.println(my_pack.get(i));
        }
    }
}
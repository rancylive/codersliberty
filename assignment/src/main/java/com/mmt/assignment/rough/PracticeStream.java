package com.mmt.assignment.rough;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PracticeStream {
	public static void main(String[] args) {
		Predicate<?> notNull = str->str!=null;
		List<String> l1=Collections.EMPTY_LIST;
		System.out.println(l1.stream().findAny().orElse("dd"));
		System.out.println("==============");
		List<String> list=Arrays.asList("apple","banana","mango","",null);
		for (String str : list) {
			System.out.println(str);
		}
		System.out.println("==============");
		list.parallelStream().forEach(str->{
			System.out.println(str);
		});
		System.out.println("==============");
		boolean ret = list.parallelStream().anyMatch(new Predicate<String>() {
			@Override
			public boolean test(String t) {
				if(t==null) {
					return false;
				}
				return t.contains("ana");
			}
		});
		System.out.println(ret+": "+list.parallelStream().count());
		System.out.println("===============");
		List<String> r1 = list.parallelStream().distinct().filter((Predicate<? super String>) notNull).collect(Collectors.toList());
		System.out.println(r1);
		System.out.println("===============");
		Optional<String> r2 = list.parallelStream().distinct().filter((Predicate<? super String>) notNull).findAny();
		System.out.println(r2.orElse(r1.get(0)));
		System.out.println("===============");
		List lengthCategory = list.stream().filter((Predicate<? super String>) notNull).map(word->word.length()).map(length->length>0?"VALID":"INVALID").collect(Collectors.toList());
		System.out.println(lengthCategory);
		System.out.println("===============");
		
		
		List<List<Integer>> matric = new ArrayList<List<Integer>>();
		matric.add(Arrays.asList(1,3,1));
		matric.add(Arrays.asList(5,2,9));
		matric.add(Arrays.asList(8,7,6));
		
		List<Boolean> addedList = matric.parallelStream().filter((Predicate<? super List<Integer>>) notNull).map(ls->ls.add(3)).collect(Collectors.toList());
		System.out.println(addedList);
		System.out.println("===============");
		
	}
}

package com.hbnu.gradesign.entity.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WelCome {

	Integer stuCounts;

	Integer teaCounts;

	Integer examCounts;

	List<Integer> years;

	List<Integer> stuCouByYea;

	List<Integer> examCouByYea;
}

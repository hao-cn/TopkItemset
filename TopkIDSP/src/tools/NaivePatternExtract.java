package tools;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.NaivePattern;
import util.Pattern;
import util.SubElementList;
import util.Value;

/**
 * author: Hao 
 * date:Feb 15, 2016
 * time:3:54:53 PM
 * purpose: count the number of naive pattern in the pattern with concise representation 
 */
public enum NaivePatternExtract {
	INSTANCE;
	
	public List<NaivePattern> extract(Pattern source){
		List<NaivePattern> list = new ArrayList<>();
		Set<NaivePattern> tmp = new HashSet<>();
		tmp.add(new NaivePattern(source));

		/** for each value in pattern source **/
		for(Value each : source.getValueList()){
			/** extract all valid subElement for each value **/
			Set<BitSet> validSub = new HashSet<>();
			/** generate all subElement of closure and test them one by one **/
			List<BitSet> allSub = SubElementList.INSTANCE.getSub(each.getClosure());
			for(BitSet sub : allSub){
				/** this sub element is covered by Value each **/
				if(covered(sub, each)){
					validSub.add(sub);
				}
			}
			
			/** construct naive patterns **/
			append(tmp, validSub);
		}
		list.addAll(tmp);
		return list;
	}
	
	/**
	 * construct naive patterns based on naive pattern in 'tmp' by append element from 'validSub'
	 */
	public void append(Set<NaivePattern> tmp, Set<BitSet> validSub) {
		Set<NaivePattern> newSet = new HashSet<>();
		/** for each pattern in 'tmp', generate |validSub| new pattern by appending one element from 'validSub' **/
		for(NaivePattern p : tmp){
			for(BitSet element : validSub){
				NaivePattern clone = p.clone();
				clone.addValue(element);
				newSet.add(clone);
			}
		}
		tmp.clear();
		tmp.addAll(newSet);
	}

	/** 
	 * 'subElement' is covered by Value 'each' or not 
	 */
	public boolean covered(BitSet element, Value each){
		//TODO 'subElement' is covered by Value 'each' or not 
		Set<BitSet> generators = each.getGenerators();
		/** if 'element' is not the sub element of any generator in 'generators',
		 * then it is covered by 'each' **/
		boolean notCovered = false;
		for(BitSet g:generators){
			BitSet tmp = (BitSet)g.clone();
			tmp.and(element);
			if(tmp.equals(element)){
				notCovered = true;
			}
		}
		return !notCovered;
	}
}

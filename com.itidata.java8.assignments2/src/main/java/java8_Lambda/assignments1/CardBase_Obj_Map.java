package java8_Lambda.assignments1;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardBase_Obj_Map {
	
    private Long card_number;
    private String card_family;
    private Long deptID;
    private Long credit_limit;
    private String customer_id;
    
    // Getter and Setter Start 
	public Long getCard_number() {
		return card_number;
	}
	public void setCard_number(Long card_number) {
		this.card_number = card_number;
	}
	public String getCard_family() {
		return card_family;
	}
	public void setCard_family(String card_family) {
		this.card_family = card_family;
	}
	public Long getDeptID() {
		return deptID;
	}
	public void setDeptID(Long deptID) {
		this.deptID = deptID;
	}
	public Long getCredit_limit() {
		return credit_limit;
	}
	public void setCredit_limit(Long credit_limit) {
		this.credit_limit = credit_limit;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	// Getter and Setter End
	
    public static Function<String, CardBase_Obj_Map> getCardbaseMap() {
        return cardbaseMap;
    }

    public static void setCardbaseMap(Function<String, CardBase_Obj_Map> cardbaseMap) {
    	CardBase_Obj_Map.cardbaseMap = cardbaseMap;
    }
    
	public static List<CardBase_Obj_Map> mapCSV(String empCSVFilePath) {
        List<CardBase_Obj_Map> cardbaseList = new ArrayList<CardBase_Obj_Map>();
        try {
            File inputF = new File(empCSVFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
// Skip the header since its just column name in table and in CSV file but not the data.
            cardbaseList = br.lines().skip(1).map(cardbaseMap).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return cardbaseList;
    }
	public static Function<String, CardBase_Obj_Map> cardbaseMap = (line) -> {
        String[] record = line.split(",");// This can be delimiter which
        CardBase_Obj_Map cardbase_Obj_map = new CardBase_Obj_Map();
        
        if (record[0] != null && record[0].trim().length() > 0) {
        	cardbase_Obj_map.setCard_number(Long.valueOf(record[0]));
        }
        if (record[1] != null && record[1].trim().length() > 0) {
        	cardbase_Obj_map.setCard_family(record[1]);
        }
        if (record[2] != null && record[2].trim().length() > 0) {
        	cardbase_Obj_map.setCredit_limit(Long.valueOf(record[2]));
        }
        if (record[3] != null && record[3].trim().length() > 0) {
        	cardbase_Obj_map.setCustomer_id(record[3]);
        }
        return cardbase_Obj_map;
    };
    public static Map<Long, Long> totalCardNumber(List<CardBase_Obj_Map> cblist) {
        return cblist.stream()
                .collect(Collectors.groupingBy(
                		CardBase_Obj_Map::getCard_number,
                        //Collectors.summingLong(CardBase_Obj_Map::getCard_number)));
                		Collectors.counting()));
    }
    public static CardBase_Obj_Map mostExpensiveResource(List<CardBase_Obj_Map> cblist) {
        return cblist.stream()
                .collect(Collectors.<CardBase_Obj_Map>maxBy(
                        Comparator.comparing(CardBase_Obj_Map::getCredit_limit))).get();
    }
    
}

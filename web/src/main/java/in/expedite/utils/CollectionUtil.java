package in.expedite.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.expedite.entity.UserRole;


public class CollectionUtil{
  
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Convert all the iteratables in to Array list 
	 * @param iterable
	 * @return
	 */
  public static <E> List<E> toList(Iterable<E> iterable) {
    if(iterable instanceof List) {
      return (List<E>) iterable;
    }
    ArrayList<E> list = new ArrayList<E>();
    if(iterable != null) {
      for(E e: iterable) {
        list.add(e);
      }
    }
    return list;
  }
  
  
  public static List<String> getList(List<UserRole> userRoles){
	  List<String> roleCode=new ArrayList<>();
	  for(UserRole userRole:userRoles){
		  roleCode.add(userRole.getRoleCode());
	  }
	return roleCode;
	  
  }
}

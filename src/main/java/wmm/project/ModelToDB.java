package wmm.project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 *@description 将model创建到数据库
 *@author <a href="wangmm_1992@163.com">阿蒙</a>
 *@date  2013-8-25 上午9:51:54
 */
public class ModelToDB {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerF = Persistence.createEntityManagerFactory("entityManager");
 		EntityManager em = entityManagerF.createEntityManager();	
	
	}

}

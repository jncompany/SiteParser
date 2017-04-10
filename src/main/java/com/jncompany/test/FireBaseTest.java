package com.jncompany.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireBaseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	    
		FirebaseDatabase fireBaseDatabase;

    	try {
    		
	        FirebaseOptions options;
				options = new FirebaseOptions.Builder()
				        .setServiceAccount(new FileInputStream("src/main/resources/firebase-spring-boot-babbd2ea2d76.json"))
				        .setDatabaseUrl("https://fir-spring-boot.firebaseio.com/")
				        .build();
	        FirebaseApp fireBaseApp = FirebaseApp.initializeApp(options);
	        fireBaseDatabase = FirebaseDatabase.getInstance(fireBaseApp);
	        DatabaseReference ref = fireBaseDatabase.getReference("blog");
	        
	        System.out.println(ref.child("posts").toString());
	        
	        
	        /**
	         * 3. Handler로 데이터 가져오기
	         */
	        ref.addValueEventListener(new ValueEventListener() {
	            @Override
	            public void onDataChange(DataSnapshot dataSnapshot) {
	                System.out.println("listner");
	                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
	                /**
	                 * 4. 데이터를 Model에 매핑하는 비동기 처리
	                 */
	                for (DataSnapshot child : children) {
	                	child.toString();
	                    //Show show = child.getValue(Show.class);
	                    //log.info("show = {}",show.getTitle() );
	                    //showList.add(show);
	                }
	            }
	            @Override
	            public void onCancelled(DatabaseError databaseError) {
	            }
	        });
	        
	        
	        
    	} catch (FileNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
	    
	}

}

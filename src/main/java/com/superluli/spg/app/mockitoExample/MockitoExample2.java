package com.superluli.spg.app.mockitoExample;

import static org.mockito.Mockito.*;

public class MockitoExample2 {

    public static void main(String[] args) {
//	Test realTest = new Test();
//	realTest.voidTest();
	Test mockTest = mock(Test.class);
	mockTest.booleanTest();
	verifyZeroInteractions(mockTest);
	
    }
}

class Test{
    
    public Test(){
	System.out.println("Test created");
    }
    
    public void voidTest(){
	System.out.println("ss");
    }
    
    public boolean booleanTest(){
	return false;
    }
    
    public Test objectTest(){
	return new Test();
    }
}

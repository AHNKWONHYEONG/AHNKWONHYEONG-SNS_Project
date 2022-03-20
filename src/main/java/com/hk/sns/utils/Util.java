package com.hk.sns.utils;

public class Util {
	//Action Tag: <jsp:usebean> 는 값을 꺼내고 저장하는 객체(DTO)를 사용할 때 쓰인다.
		//구성 Tag: <jsp:setProperty> set메서드 실행
		//		   <jsp:getProperty> get메서드 실행
		private String arrow;

		public String getArrow() {
			return arrow;
		}

		public void setArrow(String dep) {
			String nbsp="";
			int depInt=Integer.parseInt(dep);
			for(int i=0; i<depInt; i++){
				nbsp+="&nbsp;&nbsp;&nbsp;&nbsp;";
			}		
			this.arrow = nbsp+(depInt>0?"<img src='img/arrow.png'/>":"");
		}

//		public String arrowNbsp(String dep){
//			String nbsp="";
//			int depInt=Integer.parseInt(dep);
//			for(int i=0; i<depInt; i++){
//				nbsp+="&nbsp;&nbsp;&nbsp;&nbsp;";
//			}
//			return nbsp+(depInt>0?"<img src='arrow.jpg'/>":"");
//		}
}

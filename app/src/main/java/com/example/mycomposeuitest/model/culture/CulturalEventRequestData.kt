package com.example.mycomposeuitest.model.culture


data class CulturalEventRequestData(
    var key : String, //인증키
    var type : String, //json으로 고정하기
    var service : String,  //culturalEventInfo로
    var startIndex : Int, //페이징 시작번호
    var endIndex : Int,
    var codeName : String?, //?는 선택들 이건 분류
    var title : String?, //공연행사명
    var date : String? //날짜 시간 yyyy-mm-dd형식
)

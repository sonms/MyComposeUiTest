package com.example.mycomposeuitest.model.culture

data class CulturalEventResponseData(
    var listTotalCount : Int, //총 데이터 건수
    var resultCode : String, //요청결과 코드
    var resultMessage : String, //요청결과 메세지

    var codeName : String, //분류 - 콘서트, 교육행사, 등등
    var guName : String, //자치구
    var title : String, //행사명
    var date : String, //2024-01-01~2024-02-01 형식
    var place : String, //장소명
    var orgName : String, //기관명
    var useTrgt : String, //전체관람가 이런식으로 나옴
    var useFee : String,
    var player : String,
    var program : String,
    var etcDesc : String,
    var orgLink : String,
    var mainImg : String,
    var rgstdate : String,
    var ticket : String,
    var strtdate : String,
    var endDate : String,
    var themeCode : String,
    var lot : String,
    var lat : String,
    var isFree : String,
    var hmpgAddr : String,
)
/*
* 7	USE_TRGT	이용대상
8	USE_FEE	이용요금
10	PLAYER	출연자정보
11	PROGRAM	프로그램소개
12	ETC_DESC	기타내용
13	ORG_LINK	홈페이지 주소
14	MAIN_IMG	대표이미지
15	RGSTDATE	신청일
16	TICKET	시민/기관
17	STRTDATE	시작일
18	END_DATE	종료일
19	THEMECODE	테마분류
20	LOT	위도(X좌표)
21	LAT	경도(Y좌표)
22	IS_FREE	유무료
23	HMPG_ADDR	문화포털상세URL*/
<p align="center">
    <img src="https://user-images.githubusercontent.com/116870617/231695054-5832b536-59d8-4d3c-9043-7b56da96b74f.png">
</p>

# MentorZone (멘토존)
## 프로젝트 기획
- 쇼핑몰 & 관리자 모드 웹 구현 project
- 각자 자신이 갖고있거나, 이용하고싶은 타인의 재능을 사고 팔 수 있는 시장이 발달하고 있는 시대를 생각하여 재능기부마켓을 구현
- 유명한 재능기부마켓중 하나 인 크몽을 참고

## 프로젝트에 사용된 기술사항
- Language => JAVA 11, HTML4, CSS3 , javascript
- IDEA => Intelij
- DB => MySql
- FramWork => Spring boot 2.7.9 or 2.7.10
- Template Engine => Thymeleaf
- ORM => JPA

## 팀 구성 및 역할 
#### 팀장 : 김필수
- DB 설계
- 프로젝트 일정 관리 및 발표준비
- 메인 페이지, 관리자 페이지 구현
- 날씨정보 조회 구현(OpenWeather Api 연동)

#### 팀원 : 김현기
- 로그인, 회원가입 구현 
- Naver API 연동

#### 팀원 : 장기운
- 상품게시판 구현
- CI/CD 구현

#### 팀원 : 이정모
- 회원상세, 구매내역 구현


### 팀원 : 김홍록
- 상품상세, 검색기능 구현
- 장바구니, 리뷰작성 구현

<details>
<summary>상품상세, 검색기능 구현</summary>

![image](https://user-images.githubusercontent.com/116870683/235986695-45f2b2bb-a1a3-4404-8d5d-e00e043b7919.png)<br>

![image](https://user-images.githubusercontent.com/116870683/235987797-939afbf0-d935-46a1-b62e-064574de0b77.png)<br>


####상품상세 페이지
===Controller===
```

@GetMapping("/detail/{id}")
    public String search(@PathVariable("id") Long productId, Model model) {

        ProductDto product = productService.findByProduct(productId);

        if(product !=null) {
            model.addAttribute("product", product);

            List<ReviewDto> reviewDtoList = reviewService.reviewDtoListDo(productId);
            model.addAttribute("reviewDtoList", reviewDtoList);
            return "product/productDetail";
        }else{
            return "redirect:/product/productInsert";
        }

    }

```


===Service==
```
// 상품 목록 상세 페이지 가져오기
    public ProductDto findByProduct(Long id) {

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);


        if (optionalProductEntity.isPresent()) {
            return ProductDto.toProductDto(optionalProductEntity.get());
        } else {
            return null;
        }
    }

```

</details>

<details>
<summary>장바구니, 리뷰작성 구현</summary>

![image](https://user-images.githubusercontent.com/116870683/235987036-f4fcc5be-9c0d-4b81-b8b4-2f9e057340b3.png)<br>

![image](https://user-images.githubusercontent.com/116870683/235987496-23e7a19d-99d1-44f6-83cc-c308be284a2e.png)<br>


```
===Controller===
 //상품상세 장바구니 눌렀을 때
    @GetMapping("/putCart/{productId}")
    public String putCart(@PathVariable("productId") Long productId, Principal principal, Model model){

        String userEmail = principal.getName();

        MemberDto memberDto = memberService.findById(userEmail);

        Long userId = memberDto.getUserId();

        wishService.insertWish(userId, productId);

        return "redirect:/wish/cart";
    }



```

===Service==
```
public void insertWish(Long userId, Long productId) {

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);

        ProductEntity productEntity =optionalProductEntity.get();

        WishEntity wishEntity = new WishEntity();

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(userId);

        MemberEntity memberEntity = optionalMemberEntity.get();

        wishEntity.setWishOrder(1);
        wishEntity.setMemberEntity(memberEntity);
        wishEntity.setProductEntity(productEntity);

        wishRepository.save(wishEntity);

    }


```

</details>

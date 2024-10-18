## 🚀 1단계 - 복제 지연 (replication lag)

### 기능 요구사항
- [ ] 쿠폰 생성, 조회 기능 구현
   - [x] 쿠폰의 제약조건 구현
   - [x] 조회 기능은 부하 분산을 위해 reader DB의 데이터를 조회
- [ ] 복제 지연으로 인한 이슈 확인
- [ ] 복제 지연으로 인한 이슈 해결
   - [ ] 쿠폰을 생성했을 때, 의도한 대로 쿠폰이 생성됐는지 검증

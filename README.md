# ONLINE FOOD ORDER APP
## Genel Bakış

Bu proje, bir online yemek sipariş ve teslim platformunu simüle eden kapsamlı bir mikroservis mimarisidir. Farklı servislerin bir mikroservis ekosisteminde nasıl etkileşime girebileceğini,
kullanıcı yönetimi, restoran hizmetleri, inceleme işleme, kullanıcı konumuna dayalı tavsiye sağlamak için tasarlanmıştır. 
Sistem, her mikroservisin izole edilmiş, ölçeklenebilir ve kolayca dağıtılabilir olduğunu garanti eden Docker'ı konteynerizasyon için kullanır.

## Mimari
![Output](./img/architecture.png)
Mimari, ölçeklenebilirlik, dirençlilik ve servisler arası gevşek bağlantı üzerine odaklanmıştır. Her servis Docker kullanılarak konteynerize edilmiş olup, kolay dağıtım ve ölçeklendirme sağlar.
Servisler RESTful API'lar ve Kafka konuları üzerinden asenkron mesajlaşma sağlayarak yüksek kullanılabilirlik ve hata toleransı sunar. Sistem, güvenli ve verimli servisler arası iletişimi kolaylaştırmak için 
bir servis ağı kullanır.

- **User Service**: Kayıt, güncelleme ve silme dahil kullanıcı hesaplarını yönetir. Kullanıcılar, id'lere göre bulunup yönetilebilir.
- **Restaurant Service**: Restoranların açıklamalarını ve konumlarını içerir. Yakındaki restoranları bulmak için verimli arama ve mekansal sorgular yapmak üzere **Apache Solr** kullanır.
- **Advice Service**: Kullanıcı konumlarına dayalı olarak kullanıcılara kişiselleştirilmiş restoran önerileri sağlar, Kullanıcı ve Restoran servislerinden veri alır.
- **Review Service**: Kullanıcıların restoranlar için inceleme yazmalarını, güncellemelerini ve silmelerini sağlar. İncelemeler, kullanıcı geri bildirimlerine dayanarak restoranların dinamik puanlanmasına katkıda bulunur.
- **Kafka Service**: Kafka kullanılarak alınan hataları işler.
- **Prometheus ve Grafana ile İzleme**: Servislerin sağlığını ve performansını izlemek için metrikleri takip etmeyi ve görselleştirmeyi sağlar.


## Teknolojiler ve Araçlar

- Spring Boot: 3.2.3
- Docker: 4.11.1
- Apache Kafka
- Apache Solr: 4.3.14
- Prometheus ve Grafana

## Başlarken

Projeyie başlatmak için, repoyu yerel makinenize klonlayın. Docker ve Docker Compose'un yüklü olduğundan emin olun. Proje dizinine gidin ve servisleri oluşturmak ve başlatmak için Docker Compose kullanın:

``
docker-compose up --build -d
``

## Temel Endpoint'ler
### User Service

- POST /api/v1/users: Yeni bir kullanıcı oluşturur.
- GET /api/v1/users/{id}: Belirli bir ID'ye sahip kullanıcıyı getirir.
- PUT /api/v1/users: Mevcut bir kullanıcıyı günceller.
- DELETE /api/v1/users/{id}: Belirli bir ID'ye sahip kullanıcıyı siler.

### Restaurant Service

- GET /api/v1/restaurants: Tüm restoranları listeler.
- GET /api/v1/restaurants/nearby: Kullanıcının konumuna göre yakındaki restoranları listeler.
- POST /api/v1/restaurants: Yeni bir restoran oluşturur.
- PUT /api/v1/restaurants: Mevcut bir restoranı günceller.

### Review Service

- POST /api/v1/reviews: Yeni bir yorum oluşturur.
- GET /api/v1/reviews/{id}: Belirli bir ID'ye sahip yorumu getirir.
- GET /api/v1/reviews/userId/{id}: Belirli bir ID'ye sahip kullancının yorumlarını getirir.
- GET /api/v1/reviews/restaurantId{id}: Belirli bir ID'ye sahip restaurantın yorumlarını getirir.
- PUT /api/v1/reviews: Mevcut bir yorumu günceller.
- DELETE /api/v1/reviews/{id}: Belirli bir ID'ye sahip yorumu siler.

### Tavsiye Servisi

- GET /api/v1/advices/userId/{id}: Belirli bir kullanıcı ID'si için önerilen restoranları listeler. (10 km içerisindeki, kullanıcı için en yüksek uygunluktaki restaurantlar)
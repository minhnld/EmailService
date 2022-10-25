# Product Recommendation Service
This module to expose a grpc endpoint to get recommendation
## Verify the behavior
There are many tools to send requests to gRPC service to verify the behavior.
Here we used [grpcurl](https://github.com/fullstorydev/grpcurl) as it's easy to use in CLI.

Please download the proper version and make sure if it works as expected.
```bash
export END_POINT="localhost:50051"
export END_POINT="productrecommendationservice-grpc.qa.kamereo.vn:443"

# GetRecommendation
$ grpcurl  -plaintext -d '{"user_id": "xxx", "page_option": {"page":0,"size":0}}' "${END_POINT}" kamereo.item.recommendation.ProductRecommendationService/GetRecommendation


$ grpcurl -version # FYI
grpcurl 1.6.0
```
## Resources
- [gRPC Introduction](https://grpc.io/docs/what-is-grpc/introduction/)
- [gRPC Concepts](https://grpc.io/docs/what-is-grpc/core-concepts/)
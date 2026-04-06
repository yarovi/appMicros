# First go to the file directory
configuration/vault
# Second, copy the example file to the vault directory
´´´
podman cp booking-microservice.json some-vault:/vault/data
podman cp product-microservice.json some-vault:/vault/data
´´´
# third, login to vault and load the json file
podman exec -it some-vault sh
vault login root

After run command is import you locate in vault container, then run the following command to load the json file to vault

vault kv put secret/booking-microservice @data/booking-microservice.json
vault kv put secret/product-microservice @data/product-microservice.json

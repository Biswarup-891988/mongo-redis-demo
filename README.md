# mongo-redis-demo

Disadvatnage of Redis:

1. The whole dataset always resides in RAM. It can cost you.

2. The clustering solutions for Redis must be implemented in house and require a considerable effort. Redis Labs provide a cloud based Redis As Service solution.

3. Persistency affects performance. Redis uses memory dump to create a persistency snapshot (which is a great idea). But it requires some linux kernel tweaking to avoid performance degradation while Redis server process is forking.

4. Memory fragmentation issues. Writing and deleting huge amounts of data may result in performance degradation.

5. Keys management requires a considerable effort. There is no easy way to run something like SELECT COUNT(*) FROM REDIS WHERE KEY LIKE '*key*'. Latest versions of Redis are equipped with a SCAN command which makes life easier.

6. TTL management requires a discipline on application level. Time to time you can end up with Redis server overflown by the stale data because some keys have no TTL.
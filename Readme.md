# Objectives:
Implement the following linear congruential pseudo-random number generator: xn+1 = (a·xn) mod M.
Using the above random number generator, generate n integer key values starting from the seed value x0 = 98760053, and insert them into the hash table using the hash function:
h(k) = k mod m, where k is the generated key value
Then compute and display the following data: 

distribution of bucket sizes in terms of bucket size, # of buckets of this size, (# of buckets of this size)/(total # of buckets), listed in increasing order of bucket size
load factor α = n/m
standard deviation of bucket sizes from load factor (load factor is the mean value in this case)\
Using the above random number generator, generate n key values starting from the seed value x0 = 98760053, and insert them into the hash table using the linear probing sequence:
h(k, i) = (h'(k) + i) mod m,\n
h'(k) = f(k, m)

# Then compute and display the following data: \

load factor α = n/m
average # of probes performed by insertion procedure
distribution of clusters in terms of cluster size, # of clusters of this size, (# of clusters of this size)/(total # of clusters), listed in increasing order of cluster size
total # of clusters
average cluster size
standard deviation of cluster sizes
distribution of empty clusters in term of empty cluster size, # of empty clusters of this size, (# of empty clusters of this size)/(total # of empty clusters), listed in increasing order of empty cluster size
total # of empty clusters
average empty cluster size
standard deviation of empty cluster sizes

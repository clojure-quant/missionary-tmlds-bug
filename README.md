

# Reproduce of Missionary / Tablecloth bug


`clj -X:print1`

This prints the dataset with index column added.

```
row-count:  9
ds-with-idx:  _unnamed [9 3]:

| :close | :entry | :idx |
|-------:|--------|-----:|
|     40 |        |    0 |
|     40 |        |    1 |
|     50 |        |    2 |
|     80 |  :long |    3 |
|    100 |        |    4 |
|    240 |        |    5 |
|    130 | :short |    6 |
|     70 |        |    7 |
|     90 |        |    8 |

```

`clj -X:print2`


```
row-count:  2
Execution error (IllegalArgumentException) at tech.v3.dataset.base/add-or-update-column (base.clj:217).
Key must be integer

```

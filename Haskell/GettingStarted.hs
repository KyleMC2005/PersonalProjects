summ::Int -> Int
summ 0 = 0
summ n = n + summ (n-1)

main:: IO()
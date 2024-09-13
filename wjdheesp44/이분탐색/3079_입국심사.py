import sys
input = sys.stdin.readline

N, M = map(int, input().split())

times = list(int(input()) for _ in range(N))
times.sort()
result = 10**9*M//N
lt, rt = M//N, 10**9*M//N

while lt <= rt:
    mid = (lt + rt) // 2
    total = 0
    cnt = 0
    for time in times:
        cnt += mid // time

    if cnt < M:
        lt = mid + 1
    else:
        result = min(mid, result)
        rt = mid - 1

print(result)
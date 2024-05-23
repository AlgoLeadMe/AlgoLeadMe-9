n = int(input())
nums = list(map(int, input().split()))
add, sub, mul, div = map(int, input().split())

max_value = -int(1e9)
min_value = int(1e9)

def backtracking(i, total):
    global add, sub, mul, div, max_value, min_value
    if i == n:
        max_value = max(max_value, total)
        min_value = min(min_value, total)
        return 

    if add > 0:
        add -= 1
        backtracking(i+1, total + nums[i])
        add += 1
    if sub > 0:
        sub -= 1
        backtracking(i+1, total - nums[i])
        sub += 1
    if mul > 0:
        mul -= 1
        backtracking(i+1, total * nums[i])
        mul += 1
    if div > 0:
        div -= 1
        backtracking(i+1, int(total / nums[i]))
        div += 1

backtracking(1, nums[0])
print(max_value)
print(min_value)
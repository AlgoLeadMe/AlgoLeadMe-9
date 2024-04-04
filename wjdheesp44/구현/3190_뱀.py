import sys
input = sys.stdin.readline

visited = []
x, y = 1, 1
dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]
angle = 1
X = 0
snake_length = 1
target = 0

n = int(input())
board = [[0] * (n+1) for _ in range(n+1)]

apple_cnt = int(input())
apple = []
for i in range(apple_cnt):
    apple.append(list(map(int, input().split())))

for i, j in apple:
    board[i][j] = 2

dir_cnt = int(input())
dir = []
for i in range(dir_cnt):
    dir.append(list(map(str, input().split())))

while True:
    visited.append((x, y))
    nx = x + dx[angle]
    ny = y + dy[angle]
    X += 1

    if nx <= 0 or nx > n or ny <= 0 or ny > n or board[nx][ny] == 1:    # 뱀이 벽이나 자기자신에게 부딪히면 gameover
        print(X)
        break
    
    x, y = nx, ny

    if board[x][y] == 2:    # 이동한 칸에 사과가 있다면 -> 뱀 길이 늘려줌
        board[x][y] = 1
        snake_length += 1
    elif board[x][y] == 0:  # 이동한 칸에 사과가 없다면 -> 꼬리 부분 비워줌
        board[x][y] = 1
        past_x, past_y = visited[X - snake_length]
        board[past_x][past_y] = 0
    

    if int(dir[target][0]) == X:    # X초가 끝난 뒤(현재 시간과 주어진 시간 비교), 회전
        if dir[target][1] == 'L':
            angle = (angle + 3) % 4
        elif dir[target][1] == 'D':
            angle = (angle + 1) % 4
        
        if target < dir_cnt - 1:
            target += 1
        

import sys
input = sys.stdin.readline

maps = []
N, M, x, y, k = map(int, input().split())
for i in range(N):
    maps.append(list(map(int, input().split())))

dir = list(map(int, input().split()))
dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]
dice = [0, 0, 0, 0, 0, 0]

def turn(dir):
    a, b, c, d, e, f = dice[0], dice[1], dice[2], dice[3], dice[4], dice[5]
    if dir == 1:    #동
        dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = d, b, a, f, e, c
    elif dir == 2:  #서
        dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = c, b, f, a, e, d

    elif dir == 3:  #북
        dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = e, a, c, d, f, b

    else:   #남
        dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = b, f, c, d, a, e


for i in range(k):
    nx = x + dx[dir[i]-1]
    ny = y + dy[dir[i]-1]
    if nx < 0 or nx >= N or ny < 0 or ny >= M:
        continue

    x = nx
    y = ny

    turn(dir[i])
    
    if maps[nx][ny] == 0:    # 지도의 칸이 0이면
        maps[nx][ny] = dice[5]
    else:
        dice[5] = maps[nx][ny]
        maps[nx][ny] = 0

    print(dice[0])
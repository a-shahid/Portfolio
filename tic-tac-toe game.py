#Amna Shahid
#Homework 6
#This program allows the user to play a game of tic tac toe

'''
This function creates an empty board of size 3 x 3
'''
def create_empty_board ():
    new_board = [['-','-','-'],['-','-','-'],['-','-','-']]
    return new_board

'''
This funtion prints the game board onto screen
input = all the elements contained inside the board
output = board of current game
'''
def draw_board (board):
    size = len(board)   #takes a game board representation
     #creating the design for the display of columns
    col_indicator = " "
    for i in range(0, size):
        col_indicator = col_indicator + "   " + str(i)
    print(col_indicator)
    #creating the design for display of rows
    row_separator = "  " + ("-" * size * 4)
    print(row_separator)
    for row_no in range(0,len(board)):
        one_row = str(row_no) + " | "
        for cell in board[row_no]:
            if cell == '-':
                one_row += " "
            else:
            #prints the character 'o' or 'x' into the cell
                one_row += cell
            one_row += " | "
        #prints a display of the board to the screen
        print(one_row)
        print(row_separator)

'''
This function determines whether a move made by the user in the game is valid or not
input = integer inputs taken from user of row number and column number
output= boolean function of True or False
'''
def valid_move (board, col_no, row_no):
    #conditions for a move which is valid:
    #1- if the input entered is inside the range of length of board and 2-the space is empty(contains a -)
    if row_no<3 and row_no>=0 and col_no<3 and col_no>=0 and board[row_no][col_no]== '-' :
            return True
    else:
        return False

'''
This function takes inputs from the player & makes a move for that player at the position indicated by inputs
input = Two integers telling the row number and column number of board
output= an updated board
'''
def player_move (board, current_player):
    print("Player " + current_player + " moves")
    row_no= int(input("Enter a digit in the range 0-2 to indicate your chosen row "))
    col_no=int(input("Enter a digit in the range 0-2 to indicate your chosen column "))
    #keeps on asking for input if it is not valid
    while valid_move(board,col_no,row_no)==False:     #checks if input is valid by using valid_move function
            row_no=int(input("Enter a digit in the range 0-2 to indicate your chosen row "))
            col_no=int(input("Enter a digit in the range 0-2 to indicate your chosen column "))
    if valid_move(board,col_no,row_no)==True:
        board[row_no][col_no] = current_player  #updates the board

'''
This function checks whether there is a winner or not in the game being played
input = goes through the elements in board until condition is fulfilled
output = if there is winner, 'o' or 'x' is printed
'''
def win (board):
    cell=board
    #all the scenarios for winning are provided:
    #1- if a row has all the same characters
    for row_no in range(0,len(board)):
        if cell[row_no][0]==cell[row_no][1] and cell[row_no][0]==cell[row_no][2] and cell[row_no][0]!='-':
            winner = cell[row_no][0]
            print('The winner is: ', winner)
            return winner
    #2- if a column has all the same characters
    for col_no in range(0,len(board)):
        if cell[0][col_no]==cell[1][col_no] and cell[0][col_no]==cell[2][col_no] and cell[0][col_no]!='-':
            winner=cell[0][col_no]
            print('The winner is: ', winner)
            return winner
    #3- if the characters diagonally are all the same
    if (cell[0][0]==cell[1][1]==cell[2][2] and cell[1][1]!='-') or (cell[0][2]==cell[1][1]==cell[2][0] and cell[1][1]!='-'):
        winner = cell[1][1]
        print('The winner is: ', winner)
        return winner

    return None

'''
This function checks whether the board is full, i.e, if all spaces with '-' have been filled by a 'o' or 'x'
input = goes through the elements in board until condition is fulfilled
output = returns boolean value of True or False
'''
def full (board):
    for row_no in range(0,len(board)):
        for cell in board[row_no]:
            if cell=='-':             #checks for any empty slot('-') in the board
                return False
    #tells that it is a draw if the board is full and also returns True
    print("It's a draw")
    return True

'''
This function enables the game to switch from one player to the other player after each move
input = 'o' or 'x' (player currently playing)
output= 'o' or 'x' (player currently not playing)
'''
def next_player (current_player):
    if current_player=='x':
        return 'o'
    else:
        return 'x'

'''
This function allows the whole game to be played by calling other functions inside of it at appropriate times
'''
def play_ttt ():
    print("Welcome to tic-tac-toe")
    board = create_empty_board ()
    draw_board (board)
    current_player = 'x'  #sets the first playing user to 'x'
    while full(board) == False and win(board)==None:   #this while loop allows game to be continued until someone wins or board is full
            current_player = next_player(current_player)  #allows players to switch after every move 
            player_move(board, current_player)
            draw_board (board)

play_ttt()

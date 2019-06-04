import itchat


def findName(nickName):
    for friend in itchat.get_friends():
        if(friend['NickName'] == nickName):
            return True
    return False

def main():
    sendTo = "Pri_旖🍓"
    msg = "hello world"
    #sendTo = "ZFzf"
    itchat.auto_login(True)
    send_to_user = itchat.search_friends(sendTo)
    print("send to friend  %s: %s " % (send_to_user[0]['UserName'], msg))
    itchat.send(msg * 50, toUserName=send_to_user[0]['UserName'])
    itchat.logout()

if __name__=="__main__":
    main()
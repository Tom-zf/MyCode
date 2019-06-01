import numpy as np
import operator

def createDataSet():
    group  = np.array([[1,101],[5, 89],[108,5],[115,8]])
    labels = ['爱情片','爱情片','动作片','动作片']
    return group, labels

def classify(unclassifieddata, trainSet, labels, k=5):

    if(trainSet.shape[1] != len(unclassifieddata)):
        return 0
    else:
        distanceArr = trainSet - unclassifieddata  # x1-x2,y1-y2,z1-z2,...
        disDup = distanceArr ** 2                  # (x1-x2)^2,(y1-y2)^2,(z1-z2)^2,...
        distace = disDup.sum(axis=1)               # (x1-x2)^2+(y1-y2)^2+(z1-z2)^2,
        distance = distace ** 0.5                  # ((x1-x2)^2+(y1-y2)^2+(z1-z2)^2)^0.5
        sortIndex = distance.argsort()                  # 按值排序索引
        classDict = {}
        for i in range(min(k,trainSet.shape[0])):
            className = labels[sortIndex[i]]
            classCount = classDict.get(className,0) + 1
            classDict[className] = classCount
        maxClassCount = sorted(classDict.items(),key=operator.itemgetter(1),reverse=True)
        return maxClassCount[0][0]

if __name__ == '__main__':
    group,labels = createDataSet()
    predict = [101,20]
    print(classify(predict,group,labels,5))

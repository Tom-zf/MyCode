# -*- coding: utf-8 -*-
import io


class filereader(object):

    @staticmethod
    def tostr(path, encoding='utf-8'):
        lines = filereader.tolines(path, encoding=encoding)
        content_str = '';
        for i in range(len(lines)):
            content_str = "%s%s" % (content_str, lines[i])
        return content_str

    @staticmethod
    def tolines(path, encoding='utf-8'):
        lines = []
        print(path)
        with io.open(path, 'r', encoding=encoding) as file:
            line = file.readline()
            while line:
                lines.append(line)
                line = file.readline()
            return lines

class filewriter(object):


    @staticmethod
    def writestr(path, strcontent, encoding='utf-8'):
        lines = strcontent.split("\r\n")
        print(lines)
        filewriter.write(path, lines, encoding=encoding)

    @staticmethod
    def writelines(path, lines, encoding='utf-8'):
        with io.open(path, 'w', encoding=encoding) as file:
            itera = iter(lines)
            for line in itera:
                file.writelines(line)
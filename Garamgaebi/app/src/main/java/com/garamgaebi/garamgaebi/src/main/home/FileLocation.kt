package com.garamgaebi.garamgaebi.src.main.home

/**
 * 업로드할 이미지 파일이 존재
 * 업로드에 필요한 코드들이 존재할 곳
 */
class FileUpLoad{
    companion object{
        private var fileToUpLoad = ""
        fun getFileToUpLoad() = fileToUpLoad
        fun setFileToUpLoad(tempFile: String){
            fileToUpLoad = tempFile
        }
    }
}
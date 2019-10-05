/*-
 * <<
 * log-monitor
 * >
 * Copyright (C) 2019 thenx
 * >
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * >>
 */








package org.thenx.common.vo;

public class WebTool {

    public static PageIndex getPageIndex(long pagecode, int pageNow, long pageCount){
        long startpage = pageNow-(pagecode%2==0? pagecode/2-1 : pagecode/2);
        long endpage = pageNow+pagecode/2;
        if(startpage<1){
            startpage = 1;
            if(pageCount>=pagecode) endpage = pagecode;
            else endpage = pageCount;
        }
        if(endpage>pageCount){
            endpage = pageCount;
            if((endpage-pagecode)>0) startpage = endpage-pagecode+1;
            else startpage = 1;
        }
        return new PageIndex(startpage, endpage);
    }
}

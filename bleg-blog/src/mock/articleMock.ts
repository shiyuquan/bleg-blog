import Mock from 'mockjs';
import { getQueryStringByName } from '../shared/helper';
const Random = Mock.Random;

let articlePageDate: any[] = [];
export default Mock.mock(/\/api\/articles/, 'get', (oprtions: any) => {
    const url = oprtions.url;
    const pageNum = getQueryStringByName('pageNum', url);
    const pageSize = getQueryStringByName('pageSize', url);
    const totalrows = 200;
    articlePageDate = [];
    for (let i = (Number(pageNum) - 1) * Number(pageSize); i < Number(pageNum) * Number(pageSize); i++) {
        articlePageDate.push({
            month: '1',
            date: '01',
            title: '详细更新日志',
            read: i,
            comment: '666',
            // tslint:disable-next-line:max-line-length
            preface: '2015年至2017年更新内容（点击展开）20150306 近月少女的礼仪 汉化补丁v1.2120150307 黑兽 【心愿屋汉化组】汉化v1.0无弹框插件版20150307 VenusBlood -ABYSS- 更改存档路径，重新打包（汉化版本【心愿屋汉化组】v1.0）20150307 VenusBlood -FRONTIER- 更改存档路径，清除旧汉化补丁重新打包（汉化版本【心愿屋汉化组】v2.5 100%汉化）20150307 处女回路 添加汉化补丁，重新打包20150307 马上就要放暑假 无广告汉化版（汉化版本【心愿屋汉化组】v1.0）...',
        });
    }
    return {
        date: articlePageDate,
        totalPages: Math.ceil(200 / 9),
        totalrows: 200,
        pageNumber: pageNum,
        pageSize,
    };
});
